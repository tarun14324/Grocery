package com.example.grocery.user_categories

import com.example.grocery.base.BaseViewModel
import com.example.grocery.room.UserDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserCategoriesViewModel @Inject constructor(dataBase: UserDataBase) : BaseViewModel() {

    override fun refreshData() {
        // TODO("Not yet implemented")
    }

}