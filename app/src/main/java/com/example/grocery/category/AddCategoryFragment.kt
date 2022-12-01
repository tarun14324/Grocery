package com.example.grocery.category


import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentAddCategoryBinding


class AddCategoryFragment : BaseFragment<FragmentAddCategoryBinding>() {
    override fun getLayout(): Int = R.layout.fragment_add_category
    private val viewModel: CategoryViewModel by viewModels()

    override fun setUp() {
      binding.viewModel=viewModel
    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {

    }

}