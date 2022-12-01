package com.example.grocery.admin


import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentAdminBinding
import kotlinx.coroutines.flow.collectLatest


class AdminFragment : BaseFragment<FragmentAdminBinding>() {

    override fun getLayout(): Int = R.layout.fragment_admin
    private val viewModel: AdminViewModel by viewModels()

    override fun setUp() {
        binding.viewmodel = viewModel
    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categoryEventChannel.collectLatest {
                findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToCategoryFragment())
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categoryListEventChannel.collectLatest {
                findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToCategorySubItemsFragment())
            }
        }

    }
}