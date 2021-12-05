package com.bielfernandezb.samplebeerapp.presentation.beer_list.adapters

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bielfernandezb.samplebeerapp.databinding.ItemLoadingBinding
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.presentation.base.Binder

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    Binder<Beer> {

    private val itemBinding = ItemLoadingBinding.bind(itemView)

    override fun bind(unit: Beer) {
        val progressBar: ProgressBar = itemBinding.progressBar
    }

    fun showLoadingView(functionLoading: () -> Unit) {
        //ProgressBar would be displayed
        functionLoading()
    }
}