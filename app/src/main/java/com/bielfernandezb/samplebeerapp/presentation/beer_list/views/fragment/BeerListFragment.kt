package com.bielfernandezb.samplebeerapp.presentation.beer_list.views.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bielfernandezb.samplebeerapp.R
import com.bielfernandezb.samplebeerapp.core.Resource
import com.bielfernandezb.samplebeerapp.databinding.FragmentBeerListBinding
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.presentation.BaseFragment
import com.bielfernandezb.samplebeerapp.presentation.beer_list.adapters.BeerAdapter
import com.bielfernandezb.samplebeerapp.presentation.beer_list.viewmodel.BeersListViewModel
import com.bielfernandezb.samplebeerapp.presentation.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerListFragment : BaseFragment(), SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    private lateinit var binding: FragmentBeerListBinding
    private val viewModel: BeersListViewModel by viewModels()
    private lateinit var adapter: BeerAdapter<Beer>
    private var isLoading = false
    private var isSearching = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getBeers()
        setupObservers()
        initScrollListener()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = false
        searchView?.setOnQueryTextListener(this)
        searchView?.setOnCloseListener(this)
    }

    private fun setupRecyclerView() {
        adapter = createBeerAdapter(emptyList())
        binding.recyclerBeers.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerBeers.adapter = adapter
    }

    private fun createBeerAdapter(list: List<Beer>): BeerAdapter<Beer> {
        return object : BeerAdapter<Beer>(
            list,
            { onClickedBeer(it.id) },
            { }
        ) {
            override fun getLayoutId(position: Int, obj: Beer): Int {
                return R.layout.beer_list_item
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return HolderFactory.create(view, viewType)
            }
        }
    }

    private fun getBeers() {
        if (adapter.itemCount == 0) {
            viewModel.start(calcCurrentPage())
        }
    }

    private fun setupObservers() {
        viewModel.beers.distinctUntilChanged().observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    loadDone()
                    if (!it.data.isNullOrEmpty()) adapter.setItems(it.data)
                }
                Resource.Status.ERROR -> {
                    loadDone()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
        viewModel.filterBeers.distinctUntilChanged().observe(viewLifecycleOwner) { list ->
            list.let {
                adapter.setItems(it.data as List<Beer>)
            }
        }
    }

    private fun calcCurrentPage(): Int = if (adapter.itemCount == 0) {
        1
    } else {
        adapter.itemCount / 10
    }

    private fun onClickedBeer(beerId: Long) {
        Navigator().navigateToBeerDetails(this, beerId)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        query?.let {
            searchDatabase(query)
        }
        return true
    }

    override fun onClose(): Boolean {
        isSearching = false
        return false
    }

    private fun searchDatabase(query: String) {
        isSearching = true
        viewModel.setQuery(query)
    }

    private fun initScrollListener() {
        binding.recyclerBeers.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading && !isSearching) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        adapter.addItemAsLoadingPlaceHolder()
        binding.recyclerBeers.post { adapter.notifyDataSetChanged() }
        viewModel.start(calcCurrentPage() + 1)
    }

    private fun loadDone() {
        if (adapter.itemCount > 0) {
            adapter.removeItem(adapter.itemCount - 1)
            adapter.notifyDataSetChanged()
        }
        isLoading = false
    }
}