package com.example.grocery.early_delivery

import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentEarlyDeliveryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarlyDeliveryFragment : BaseFragment<FragmentEarlyDeliveryBinding>() {

    private val viewModel: EarlyDeliveryViewModel by viewModels()
    override fun getLayout(): Int = R.layout.fragment_early_delivery

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        //    TODO("Not yet implemented")
    }

    override fun setUp() {
        binding.viewModel = viewModel
    }
}