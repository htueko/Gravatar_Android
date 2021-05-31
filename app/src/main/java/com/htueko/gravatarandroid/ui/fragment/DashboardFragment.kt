package com.htueko.gravatarandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.htueko.gravatarandroid.R
import com.htueko.gravatarandroid.data.local.entity.Person
import com.htueko.gravatarandroid.databinding.FragmentDashboardBinding
import com.htueko.gravatarandroid.ui.adapter.AppAdapter
import com.htueko.gravatarandroid.ui.viewmodel.AppViewModel
import com.htueko.gravatarandroid.util.Utility
import com.htueko.mayremo.util.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    companion object {
        const val defaultSpanCount = 1
        const val defaultGridViewWidth = 56.0f
        const val defaultRecyclerViewStartPosition = 0
    }

    private var layoutManger: GridLayoutManager? = null
    private lateinit var appAdapter: AppAdapter
    private val viewModel: AppViewModel by viewModels()
    private val personList: MutableList<Person> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        //
        viewModel.getPeople()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        setupRecyclerView()
        observeData()
        setupMenuOnItemClick()
        setupSimulateButtonClickListener(personList)
    }

    private fun setupMenu() {
        binding.toolbar.inflateMenu(R.menu.dashboard_toolbar_menu)
    }

    private fun setupMenuOnItemClick() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_list_view -> {
                    layoutManger?.spanCount = defaultSpanCount
                    updateRecyclerViewRanged()
                    true
                }
                R.id.action_grid_view -> {
                    val numberOfColumns =
                        Utility.calculateNoOfColumns(getApplicationContext(), defaultGridViewWidth)
                    layoutManger?.spanCount = numberOfColumns
                    updateRecyclerViewRanged()
                    true
                }
                else -> {
                    layoutManger?.spanCount = defaultSpanCount
                    false
                }
            }
        }
    }

    private fun setupRecyclerView() {
        layoutManger = GridLayoutManager(requireContext(), defaultSpanCount)
        appAdapter = AppAdapter(layoutManger)
        postponeEnterTransition()
        binding.recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
        binding.recyclerView.apply {
            layoutManager = layoutManger
            adapter = appAdapter
        }
    }

    private fun updateRecyclerViewRanged() {
        appAdapter.notifyItemRangeChanged(
            defaultRecyclerViewStartPosition,
            appAdapter.itemCount
        )
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.peopleLiveData.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is State.Loading -> {
                        showProgress(true)
                        //showError(null, false)
                    }
                    is State.Error -> {
                        showProgress(false)
                        showError(it.message, true)
                    }
                    is State.Success -> {
                        showProgress(false)
                        //showError(null, false)
                        personList?.addAll(it.data)
                        appAdapter.data = viewModel.multiplyData(it.data)
                    }
                }
            })
        }
    }

    private fun setupSimulateButtonClickListener(data: List<Person>) {
        binding.btnRandom.setOnClickListener {
            val result = viewModel.shuffleData(data)
            appAdapter.data = result
            updateRecyclerViewRanged()
        }
    }

    private fun showProgress(value: Boolean) {
        if (value) {
            if (!binding.progress.isShown) binding.progress.visibility = View.VISIBLE
        } else {
            binding.progress.visibility = View.GONE
        }
    }

    private fun showError(message: String?, value: Boolean) {
        if (value) {
            if (!binding.tvError.isVisible) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = message
            }
        } else {
            binding.tvError.visibility = View.GONE
        }
    }

}
