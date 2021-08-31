package com.feragusper.theforklite.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.feragusper.theforklite.R
import com.feragusper.theforklite.adapter.ImageGalleryAdapter
import com.feragusper.theforklite.databinding.FragmentRestaurantDetailBinding
import com.feragusper.theforklite.viewmodel.RestaurantDetailUiState
import com.feragusper.theforklite.viewmodel.RestaurantDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

    private val viewModel: RestaurantDetailViewModel by viewModels()
    private val adapter = ImageGalleryAdapter()

    private val restaurantId by lazy { "380717" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.imageGalleryViewPager.adapter = adapter
        TabLayoutMediator(binding.imageGalleryTabLayout, binding.imageGalleryViewPager) { _, _ -> }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setHasOptionsMenu(true)

        subscribeUi(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchRestaurant(restaurantId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.restaurant_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun subscribeUi(binding: FragmentRestaurantDetailBinding) {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is RestaurantDetailUiState.Loading -> {
                        showLoading(binding, true)
                    }
                    is RestaurantDetailUiState.Success -> {
                        showLoading(binding, false)
                        val restaurant = state.restaurant
                        binding.restaurant = restaurant
                        adapter.submitList(restaurant.picsDiaporamaImageUrls)
                    }
                    is RestaurantDetailUiState.Error -> {
                        showLoading(binding, false)
                        Toast.makeText(context, state.exception.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun showLoading(binding: FragmentRestaurantDetailBinding, visible: Boolean) {
        binding.loading.isVisible = visible
        binding.bookButton.isVisible = visible.not()
    }

}
