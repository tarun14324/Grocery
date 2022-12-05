package com.example.grocery.category_sub_items


import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentAddCategoryListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddCategoryList : BaseFragment<FragmentAddCategoryListBinding>() {

    override fun getLayout(): Int = R.layout.fragment_add_category_list
    private val viewModel: CategoryListViewModel by viewModels()
    private var list: ArrayList<String> = arrayListOf()
    private val selectImageResultContract =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel.showImage.value = true
                viewModel.categoryImagePath.value = uri.toString()
                binding.ivCategory.setImageURI(uri)
            }
        }

    private val arrayAdapter: ArrayAdapter<String>
        get() {
            return ArrayAdapter(
                requireContext(),
                R.layout.dropdown_item,
                list
            )
        }

    override fun setUp() {
        binding.viewModel = viewModel
        val adapter = arrayAdapter
        binding.autoCompleteTextView.setAdapter(adapter)
    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.addImageEventChannel.collectLatest {
                selectImageFromGallery()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.addCategoryEventChannel.collectLatest {
                findNavController().popBackStack()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.toastEventChannel.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categoryList.collectLatest {
                list.clear()
                list.addAll(it)
                arrayAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun selectImageFromGallery() {
        selectImageResultContract.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}