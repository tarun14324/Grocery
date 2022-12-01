package com.example.grocery.home


import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getLayout(): Int = R.layout.fragment_home
    private val homeViewModel: HomeViewModel by viewModels()

    override fun setUp() {
        binding.viewModel = homeViewModel
    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            homeViewModel.adminEventChannel.collectLatest {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAdminFragment())
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            homeViewModel.userEventChannel.collectLatest {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUserFragment2())
            }
        }

    }
}