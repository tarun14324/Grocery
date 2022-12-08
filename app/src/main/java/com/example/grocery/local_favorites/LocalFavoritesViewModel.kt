package com.example.grocery.local_favorites

import com.example.grocery.base.BaseViewModel
import com.example.grocery.room.UserDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalFavoritesViewModel @Inject constructor(dataBase: UserDataBase) : BaseViewModel() {

    override fun refreshData() {
        // TODO("Not yet implemented")
    }

}