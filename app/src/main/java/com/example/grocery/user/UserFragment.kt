package com.example.grocery.user


import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager2.widget.ViewPager2
import com.example.grocery.R
import com.example.grocery.base.BaseFragment
import com.example.grocery.databinding.FragmentUserBinding
import com.example.grocery.user_home.UserTabItemsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {

    override fun getLayout(): Int = R.layout.fragment_user
    private val viewModel: UserViewModel by viewModels()

    override fun setUp() {
        binding.viewModel = viewModel
        binding.viewPager.adapter = UserTabItemsAdapter(this)
        setUpViewPager()
        tabLayout()
    }

    private fun tabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //  TODO("Not yet implemented")
            }
        })
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.viewPager.currentItem = position
            }
        })
    }

    private fun setUpViewPager() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = viewModel.list[position]
        }.attach()
    }

    override fun getData(viewLifecycleOwner: LifecycleOwner) {

    }
}