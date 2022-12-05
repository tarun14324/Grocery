package com.example.grocery.category


import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentAddCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class AddCategoryFragment : BaseFragment<FragmentAddCategoryBinding>() {

    override fun getLayout(): Int = R.layout.fragment_add_category
    private val viewModel: CategoryViewModel by viewModels()
    private val args: AddCategoryFragmentArgs by navArgs()

    private val selectImageResultContract =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel.showImage.value = true
                viewModel.categoryImagePath.value = uri.toString()
                binding.ivCategory.setImageURI(uri)
            }
        }

    override fun setUp() {
        binding.viewModel = viewModel
        viewModel.categoryName.value = args.category.name
        viewModel.categoryId.value = args.category.id
        viewModel.isUpdateCategory.value = args.category.isUpdateCategory
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
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.toastEventChannel.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun selectImageFromGallery() {
        selectImageResultContract.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

}