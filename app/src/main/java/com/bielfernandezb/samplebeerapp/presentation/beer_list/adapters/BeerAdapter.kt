package com.bielfernandezb.samplebeerapp.presentation.beer_list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bielfernandezb.samplebeerapp.R
import com.bielfernandezb.samplebeerapp.domain.entity.Beer
import com.bielfernandezb.samplebeerapp.presentation.base.BaseAdapter

abstract class BeerAdapter<T>(
    list: List<T>,
    private val onBeerClicked: ((data: Beer) -> Unit),
    private val showLoadingView: ((Unit) -> Unit),
) : BaseAdapter<T>(list.toMutableList()) {

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    fun setItems(items: List<T>) {
        list = items
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        val newList = list.toMutableList().removeAt(position)
        list = listOf(newList)
    }

    fun addItemAsLoadingPlaceHolder() {
        val newList = list.toMutableList()
        val original: Beer = list[itemCount - 1] as Beer
        val placeHolder = original.copy()
        placeHolder.id = -1L
        newList.add(placeHolder as T)
        list = newList
    }

    override fun getItemViewType(position: Int): Int {
        return if ((list[position] as Beer).id == -1L) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder is BeerViewHolder) {
            holder.onBeerClicked { onBeerClicked(list[position] as Beer) }
        } else if (holder is LoadingViewHolder) {
            holder.showLoadingView { showLoadingView(Unit) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = if (viewType == VIEW_TYPE_ITEM) {
            val itemView: View =
                LayoutInflater.from(parent.context).inflate(R.layout.beer_list_item, parent, false)
            BeerViewHolder(itemView)
        } else {
            val itemView: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(itemView)
        }
        return viewHolder
    }

    object HolderFactory {
        fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == VIEW_TYPE_ITEM) {
                BeerViewHolder(view)
            } else {
                LoadingViewHolder(view)
            }
        }
    }

}