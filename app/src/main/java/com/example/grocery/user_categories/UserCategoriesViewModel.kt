package com.example.grocery.user_categories

import androidx.lifecycle.viewModelScope
import com.example.grocery.base.BaseViewModel
import com.example.grocery.room.UserDataBase
import com.example.grocery.room.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserCategoriesViewModel @Inject constructor(private val dataBase: UserDataBase) :
    BaseViewModel() {
    var categoryList = MutableStateFlow(listOf<UserEntity>())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryList.value = dataBase.userDao().getCategory()
        }
    }

    override fun refreshData() {
    //    TODO("Not yet implemented")
    }

}