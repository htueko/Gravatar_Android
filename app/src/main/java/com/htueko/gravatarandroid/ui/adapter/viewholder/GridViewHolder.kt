package com.htueko.gravatarandroid.ui.adapter.viewholder

import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.htueko.gravatarandroid.R
import com.htueko.gravatarandroid.data.local.entity.Person
import com.htueko.gravatarandroid.databinding.ItemGridBinding
import com.htueko.gravatarandroid.ui.fragment.DashboardFragmentDirections


class GridViewHolder(private val binding: ItemGridBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: Person) {
        ViewCompat.setTransitionName(binding.imvAvatarGrid, user.name)
        Glide.with(binding.root.context)
            .load(user.imageUrl)
            .into(binding.imvAvatarGrid)
        val res = binding.root.context.resources
        if (user.isOnline) {
            binding.imvStatusGrid.setBackgroundColor(res.getColor(R.color.status_online))
        } else {
            binding.imvStatusGrid.setBackgroundColor(res.getColor(R.color.status_offline))
        }
        //
        binding.root.setOnClickListener {
            val action =
                DashboardFragmentDirections.actionDashboardFragmentToDetailFragment(user)
            val extras = FragmentNavigatorExtras(binding.imvAvatarGrid to user.name)
            it.findNavController()
                .navigate(action, extras)
        }
    }
}