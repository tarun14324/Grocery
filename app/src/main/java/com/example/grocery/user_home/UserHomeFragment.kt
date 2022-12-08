package com.example.grocery.user_home

import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentUserHomeBinding
import com.example.grocery.model.ProductDetails
import com.example.grocery.model.SingleProductGrid
import com.example.grocery.room.UserEntity
import com.example.grocery.user_home.adapters.AllCategoriesAdapter
import com.example.grocery.user_home.adapters.DealsAdapter
import com.example.grocery.user_home.adapters.StealDealsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class UserHomeFragment : BaseFragment<FragmentUserHomeBinding>() {

    private val viewModel: UserHomeViewModel by viewModels()
    override fun getLayout(): Int = R.layout.fragment_user_home

    private val adapter = AllCategoriesAdapter {
        onItemClicked(it)
    }
    private val stealDealsAdapter = StealDealsAdapter(onItemCLicked = {
        //on item clicked
    })
    private val dealsAdapter = DealsAdapter(onItemCLicked = {

    })

    private fun onItemClicked(it: UserEntity) {

    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categoryList.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun setUp() {
        binding.viewModel = viewModel
        binding.rvHomeCategory.adapter = adapter
        binding.rvStealDeals.adapter = stealDealsAdapter
        binding.rvAllDeals.adapter = dealsAdapter
        binding.rvHomeCategory.itemAnimator = null
        binding.rvStealDeals.itemAnimator = null
        binding.rvAllDeals.itemAnimator = null
        initializeData()
    }

    private fun initializeData() {
        val steallist = listOf(
            ProductDetails(
                0,
                "atta",
                "https://media.istockphoto.com/id/164981421/photo/large-group-of-food-shoot-on-white-backdrop.jpg?s=612x612&w=0&k=20&c=S3UjegrKBG-HyZdYQmOeBCk1Cfk7C7XZrUGb0n56Gy8=",
                200,
                categoryItemWeight = "1kg",
                "",
                ""
            ),
            ProductDetails(
                id = 0,
                categoryName = "atta2",
                categoryImagePath = "https://media.istockphoto.com/id/164981421/photo/large-group-of-food-shoot-on-white-backdrop.jpg?s=612x612&w=0&k=20&c=S3UjegrKBG-HyZdYQmOeBCk1Cfk7C7XZrUGb0n56Gy8=",
                categoryItemPrice = 40,
                categoryItemWeight = "100g",
                categoryItemDesc = "well statute-rated",
                categoryItemName = "chakra"
            )
        )
        stealDealsAdapter.submitList(steallist)
        val dealsList = listOf(
            SingleProductGrid(
                "atta 1", "chakra", "https://media.istockphoto.com/id/164981421/photo/large-group-of-food-shoot-on-white-backdrop.jpg?s=612x612&w=0&k=20&c=S3UjegrKBG-HyZdYQmOeBCk1Cfk7C7XZrUGb0n56Gy8=", 100, 20, 0
            ),
            SingleProductGrid(
                "att2",
                "malabar",
                "https://media.istockphoto.com/id/164981421/photo/large-group-of-food-shoot-on-white-backdrop.jpg?s=612x612&w=0&k=20&c=S3UjegrKBG-HyZdYQmOeBCk1Cfk7C7XZrUGb0n56Gy8=",
                30,
                10,
                0
            ),
            SingleProductGrid(
                "att3",
                "malabar",
                "https://media.istockphoto.com/id/164981421/photo/large-group-of-food-shoot-on-white-backdrop.jpg?s=612x612&w=0&k=20&c=S3UjegrKBG-HyZdYQmOeBCk1Cfk7C7XZrUGb0n56Gy8=",
                60,
                30,
                0
            ),
            SingleProductGrid(
                "att4",
                "malabar",
                "https://media.istockphoto.com/id/164981421/photo/large-group-of-food-shoot-on-white-backdrop.jpg?s=612x612&w=0&k=20&c=S3UjegrKBG-HyZdYQmOeBCk1Cfk7C7XZrUGb0n56Gy8=",
                80,
                15,
                0
            ),
        )
        dealsAdapter.submitList(dealsList)
    }


}