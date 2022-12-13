package com.example.grocery.details

import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentProductDetailBinding
import com.example.grocery.util.AddOrRemoveCallbacks
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>(), AddOrRemoveCallbacks {
    override fun getLayout(): Int = R.layout.fragment_product_detail

    private val args: ProductDetailFragmentArgs by navArgs()
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.addLayoutEventChannel.collectLatest {
                binding.showAddLayout = false
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.addEventChannel.collectLatest {
                onAddProductIncreased()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.removeEventChannel.collectLatest {
                onAddProductDecreased()
            }
        }
    }

    override fun setUp() {
        binding.details = args.category
        binding.viewModel = viewModel
        binding.showAddLayout = true
    }

    override fun onAddProductIncreased() {
        if (viewModel.itemQuantity.value < 10) {
            viewModel.itemQuantity.value++
        }
    }

    override fun onAddProductDecreased() {
        if (viewModel.itemQuantity.value > 0) {
            viewModel.itemQuantity.value--
        } else {
            binding.showAddLayout = true
        }
    }
}