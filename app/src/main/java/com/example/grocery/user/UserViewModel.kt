package com.example.grocery.user

import com.example.grocery.base.BaseViewModel
import com.example.grocery.room.UserDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(dataBase: UserDataBase) : BaseViewModel() {

    val list = listOf("Home", "Categories", "Local Favorites", "45 Min.Delivery")
    override fun refreshData() {
        // TODO("Not yet implemented")
    }

}