package com.bielfernandezb.samplebeerapp.presentation.beer_list.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bielfernandezb.samplebeerapp.databinding.BeerListItemBinding
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.presentation.base.Binder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    Binder<Beer> {

    private val itemBinding = BeerListItemBinding.bind(itemView)

    override fun bind(data: Beer) {
        itemBinding.textBeerName.text = data.name
        itemBinding.textBeerAbvValue.text = data.description
        Glide.with(itemBinding.root)
            .load(data.imageURL)
            .transform(CircleCrop())
            .into(itemBinding.imageBeer)
    }

    fun onBeerClicked(functionBeer: () -> Unit) {
        itemBinding.root.setOnClickListener {
            functionBeer()
        }
    }
}