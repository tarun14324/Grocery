package com.example.grocery.category


import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentCategoryBinding
import com.example.grocery.room.UserEntity
import kotlinx.coroutines.flow.collectLatest


class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override fun getLayout(): Int = R.layout.fragment_category

    val list = ArrayList<UserEntity>()

    var adapter = CategoryAdapter {
        onEditButtonClicked(it)
    }

    private val viewModel: CategoryViewModel by viewModels()

    override fun setUp() {
        binding.viewModel = viewModel
        initializeData()
        binding.rvcategory.adapter = adapter
        adapter.submitList(list)
    }

    private fun initializeData() {
        for (i in 1..10)
            list.add(UserEntity(0, "name-$i", R.drawable.apple.toString()))
    }


    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.addCategoryEventChannel.collectLatest {
                findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToAddCategory())
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