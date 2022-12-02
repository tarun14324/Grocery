package com.example.grocery.category


import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentAddCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class AddCategoryFragment : BaseFragment<FragmentAddCategoryBinding>() {
    override fun getLayout(): Int = R.layout.fragment_add_category
    private val viewModel: CategoryViewModel by viewModels()

    private val selectImageResultContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                viewModel.showImage.value = true
                viewModel.categoryImagePath.value = uri.toString()
                binding.ivCategory.setImageURI(uri)
            }
        }


    override fun setUp() {
        binding.viewModel = viewModel
    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.addImageEventChannel.collectLatest {
                selectImageFromGallery()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.addDataBaseCreatedChannel.collectLatest {
                findNavController().popBackStack()
            }
        }

    }

    private fun selectImageFromGallery() {
        selectImageResultContract.launch("image/*")
    }

}