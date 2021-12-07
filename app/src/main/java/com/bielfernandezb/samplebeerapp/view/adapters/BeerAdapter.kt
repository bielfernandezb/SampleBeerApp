package com.bielfernandezb.samplebeerapp.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bielfernandezb.samplebeerapp.databinding.BeerListItemBinding
import com.bielfernandezb.samplebeerapp.model.entities.Beer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class BeerAdapter(private val listener: BeerItemListener) : RecyclerView.Adapter<BeerViewHolder>() {

    interface BeerItemListener {
        fun onClickedBeer(beerId: Long)
    }

    private val items = ArrayList<Beer>()

    fun setItems(items: ArrayList<Beer>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val binding: BeerListItemBinding =
            BeerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) =
        holder.bind(items[position])
}

class BeerViewHolder(
    private val itemBinding: BeerListItemBinding,
    private val listener: BeerAdapter.BeerItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var beer: Beer

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Beer) {
        this.beer = item
        itemBinding.beerName.text = item.name
        itemBinding.abvValue.text = item.description
        Glide.with(itemBinding.root)
            .load(item.imageURL)
            .transform(CircleCrop())
            .into(itemBinding.beerImg)
    }

    override fun onClick(v: View?) {
        listener.onClickedBeer(beer.id)
    }
}