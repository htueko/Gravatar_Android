package com.htueko.gravatarandroid.ui.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.htueko.gravatarandroid.R
import com.htueko.gravatarandroid.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val args by navArgs<DetailFragmentArgs>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAnimation()
        setupUi()
    }

    private fun setupUi() {
        val person = args.person
        binding.apply {
            binding.imvAvatarDetail.transitionName = person.name
            tvNameDetail.text = person.name
            tvEmailDetail.text = "${person.name.lowercase()}@abc.com"
            if (person.isOnline) {
                tvStatusDetail.text = resources.getText(R.string.text_online)
                tvStatusDetail.setTextColor(resources.getColor(R.color.status_online))
            } else {
                tvStatusDetail.text = resources.getText(R.string.text_offline)
                tvStatusDetail.setTextColor(resources.getColor(R.color.status_offline))
            }
            Glide.with(binding.root.context)
                .load(person.imageUrl)
                .into(binding.imvAvatarDetail)
        }
    }

    private fun setupAnimation() {
        val animation = TransitionInflater
            .from(requireContext())
            .inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)
    }

}