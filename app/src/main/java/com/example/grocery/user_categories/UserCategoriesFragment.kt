package com.example.grocery.user_categories

import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentUserCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserCategoriesFragment : BaseFragment<FragmentUserCategoriesBinding>() {

    private val viewModel: UserCategoriesViewModel by viewModels()


    override fun getLayout(): Int = R.layout.fragment_user_categories

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        //    TODO("Not yet implemented")
    }

    override fun setUp() {
        binding.viewModel = viewModel
    }
}