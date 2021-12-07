package com.bielfernandezb.samplebeerapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bielfernandezb.samplebeerapp.R
import com.bielfernandezb.samplebeerapp.databinding.FragmentBeerListBinding
import com.bielfernandezb.samplebeerapp.model.entities.Beer
import com.bielfernandezb.samplebeerapp.utils.Resource
import com.bielfernandezb.samplebeerapp.view.BeersListViewModel
import com.bielfernandezb.samplebeerapp.view.activities.BeerDetailsActivity
import com.bielfernandezb.samplebeerapp.view.adapters.BeerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BeerListFragment : Fragment(), BeerAdapter.BeerItemListener, SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentBeerListBinding
    private val viewModel: BeersListViewModel by viewModels()
    private lateinit var adapter: BeerAdapter
    private var currentPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeerListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }


    private fun setupRecyclerView() {
        adapter = BeerAdapter(this)
        binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerList.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.start(currentPage)
        viewModel.beers.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.start(++currentPage)
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onClickedBeer(beerId: Long) {
        val intent = Intent(activity, BeerDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putLong("beerId", beerId)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = query

        viewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                adapter.setItems(it.data as ArrayList<Beer>)
            }
        })
    }
}