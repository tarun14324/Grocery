package com.example.grocery.user_home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.grocery.early_delivery.EarlyDeliveryFragment
import com.example.grocery.local_favorites.LocalFavoritesFragment
import com.example.grocery.user_categories.UserCategoriesFragment

class UserTabItemsAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserHomeFragment()
            1 -> UserCategoriesFragment()
            2 -> LocalFavoritesFragment()
            3 -> EarlyDeliveryFragment()
            else -> {
                UserHomeFragment()
            }
        }
    }
}