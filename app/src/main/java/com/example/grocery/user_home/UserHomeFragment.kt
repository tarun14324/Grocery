package com.example.grocery.user_home

import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentUserHomeBinding
import com.example.grocery.room.CategoryListItems
import com.example.grocery.room.UserEntity
import com.example.grocery.user.UserFragmentDirections
import com.example.grocery.user_home.adapters.*
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
        onProductClicked(it)
    })
    private val bestSellerListAdapter = StealDealsAdapter(onItemCLicked = {
        onProductClicked(it)
    })
    private val groomingListAdapter = StealDealsAdapter(onItemCLicked = {
        onProductClicked(it)
    })
    private val cookingListAdapter = StealDealsAdapter(onItemCLicked = {
        onProductClicked(it)
    })
    private val foodListAdapter = StealDealsAdapter(onItemCLicked = {
        onProductClicked(it)
    })
    private val frequntlyBoughtListAdapter = DealsAdapter(onItemCLicked = {
        onProductClicked(it)
    })
    private val fruitsListAdapter = StealDealsAdapter(onItemCLicked = {
        onProductClicked(it)
    })

    private val dealsAdapter = DealsAdapter(onItemCLicked = {
        onProductClicked(it)
    })

    private fun onItemClicked(it: UserEntity) {

    }

    private fun onProductClicked(it: CategoryListItems) {
        val action = UserFragmentDirections.actionUserFragmentToProductDetailFragment(it)
        findNavController().navigate(action)

    }

    private val trendingStoresAdapter = ImageAdapter {

    }

    private val sponsoredAdapter = SponsoredAdapter {

    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categoryList.collectLatest {
                adapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categorySubItems.collectLatest {
                bestSellerListAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.categorySubItems.collectLatest {
                stealDealsAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.bestSellerList.collectLatest {
                bestSellerListAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.groomingList.collectLatest {
                groomingListAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.fruitsList.collectLatest {
                fruitsListAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.frequntlyBoughtList.collectLatest {
                frequntlyBoughtListAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.cookingList.collectLatest {
                cookingListAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.foodList.collectLatest {
                foodListAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.SponsoredList.collectLatest {
                sponsoredAdapter.submitList(it)
            }
        }
    }

    override fun setUp() {
        binding.viewModel = viewModel
        binding.rvHomeCategory.adapter = adapter
        binding.rvStealDeals.adapter = stealDealsAdapter
        binding.layoutAllCategories.rvCategory.adapter = adapter
        binding.rvTrendingStores.adapter = trendingStoresAdapter
        binding.includeLayout1.rvAllDeals.adapter = stealDealsAdapter
        binding.includeLayout2.rvAllDeals.adapter = cookingListAdapter
        binding.includeLayout3.rvAllDeals.adapter = cookingListAdapter
        binding.layoutSponsored.rvSponsored.adapter = sponsoredAdapter
        binding.layoutFrequentlyBought.rvfrequentlyBoughtDeals.adapter = frequntlyBoughtListAdapter
    }
}