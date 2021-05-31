package com.htueko.gravatarandroid.ui.adapter.viewholder

import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.htueko.gravatarandroid.R
import com.htueko.gravatarandroid.data.local.entity.Person
import com.htueko.gravatarandroid.databinding.ItemListBinding
import com.htueko.gravatarandroid.ui.fragment.DashboardFragmentDirections

class ListViewHolder(private val binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(user: Person) {
        ViewCompat.setTransitionName(binding.imvAvatarList, user.name)
        binding.apply {
            tvNameList.text = user.name
            Glide.with(binding.root.context)
                .load(user.imageUrl)
                .into(binding.imvAvatarList)
            val res = binding.root.context.resources
            if (user.isOnline) {
                binding.imvStatusList.setBackgroundColor(res.getColor(R.color.status_online))
            } else {
                binding.imvStatusList.setBackgroundColor(res.getColor(R.color.status_offline))
            }
            //
            binding.root.setOnClickListener {
                val action =
                    DashboardFragmentDirections.actionDashboardFragmentToDetailFragment(user)
                val extras = FragmentNavigatorExtras(binding.imvAvatarList to user.name)
                it.findNavController()
                    .navigate(action, extras)
            }
        }
    }
}
