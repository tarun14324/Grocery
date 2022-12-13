package com.example.grocery.user_categories

import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentUserCategoriesBinding
import com.example.grocery.room.UserEntity
import com.example.grocery.user_home.adapters.AllCategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UserCategoriesFragment : BaseFragment<FragmentUserCategoriesBinding>() {

    private val viewModel: UserCategoriesViewModel by viewModels()

    private val adapter = AllCategoriesAdapter {
        onItemClicked(it)
    }

    private fun onItemClicked(it: UserEntity) {
//
    }

    override fun getLayout(): Int = R.layout.fragment_user_categories

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categoryList.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun setUp() {
        binding.viewModel = viewModel
        binding.rvCategory.adapter = adapter
        binding.cardView.setOnClickListener {
            //move to dryFruits collection page
        }

    }
}