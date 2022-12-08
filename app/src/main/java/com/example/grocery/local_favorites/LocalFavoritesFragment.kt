package com.example.grocery.local_favorites

import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentLocalFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalFavoritesFragment : BaseFragment<FragmentLocalFavoritesBinding>() {

    private val viewModel: LocalFavoritesViewModel by viewModels()
    override fun getLayout(): Int = R.layout.fragment_local_favorites

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        //    TODO("Not yet implemented")
    }

    override fun setUp() {
        binding.viewModel = viewModel
    }
}