package com.example.grocery

import com.example.grocery.room.UserDataBase
import javax.inject.Inject

class UserRepository @Inject constructor(dataBase: UserDataBase) {
    val categoryList=dataBase.userDao().getCategory()
}