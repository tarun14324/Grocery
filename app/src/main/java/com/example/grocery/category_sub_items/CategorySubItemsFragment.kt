package com.example.grocery.category_sub_items


import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentCategorySubItemsBinding
import com.example.grocery.room.CategoryListItems
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class CategorySubItemsFragment : BaseFragment<FragmentCategorySubItemsBinding>() {

    private val viewModel: CategoryListViewModel by viewModels()

    private var adapter = CategorySubItemsListAdapter {
        onItemCLicked(it)
    }

    override fun getLayout(): Int = R.layout.fragment_category_sub_items

    override fun setUp() {
        binding.viewModel = viewModel
        binding.rvcategory.adapter = adapter
    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categoryEventChannel.collectLatest {
                findNavController().navigate(CategorySubItemsFragmentDirections.actionCategorySubItemsFragmentToCreateCategoryList())
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categorySubItems.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    private fun onItemCLicked(item: CategoryListItems) {
        // navgate to details fragment
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }
}