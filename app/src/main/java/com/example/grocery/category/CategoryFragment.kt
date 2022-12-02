package com.example.grocery.category


import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentCategoryBinding
import com.example.grocery.room.UserEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override fun getLayout(): Int = R.layout.fragment_category

    val list = ArrayList<UserEntity>()

    var adapter = CategoryAdapter {
        onEditButtonClicked(it)
    }
    private val viewModel: CategoryViewModel by viewModels()

    override fun setUp() {
        binding.viewModel = viewModel
        binding.rvcategory.adapter = adapter
        adapter.submitList(list)
    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.addCategoryEventChannel.collectLatest {
                findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToAddCategory())
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categoryList.collectLatest {
                adapter.submitList(it)
            }
        }
    }


    private fun onEditButtonClicked(item: UserEntity) {
        findNavController().navigate(
            CategoryFragmentDirections.actionCategoryFragmentToAddCategory(
                //item
            )
        )
    }
}