package com.htueko.gravatarandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.htueko.gravatarandroid.data.local.entity.Person
import com.htueko.gravatarandroid.databinding.ItemGridBinding
import com.htueko.gravatarandroid.databinding.ItemListBinding
import com.htueko.gravatarandroid.ui.adapter.viewholder.GridViewHolder
import com.htueko.gravatarandroid.ui.adapter.viewholder.ListViewHolder

class AppAdapter(private val layoutManager: GridLayoutManager? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        List,
        Grid
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var data: List<Person>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.List.ordinal -> {
                val binding =
                    ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ListViewHolder(binding)
            }
            else -> {
                val binding =
                    ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GridViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = data[position]
        when (holder.itemViewType) {
            ViewType.List.ordinal -> {
                (holder as ListViewHolder).bind(currentItem)
            }
            else -> {
                (holder as GridViewHolder).bind(currentItem)
            }
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.List.ordinal
        else ViewType.Grid.ordinal
    }

}