package com.example.grocery.category

import androidx.lifecycle.viewModelScope
import com.example.grocery.base.BaseViewModel
import com.example.grocery.room.UserDataBase
import com.example.grocery.room.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val userDataBase: UserDataBase) :
    BaseViewModel() {

    private val _addCategoryEventChannel = Channel<Unit>(Channel.CONFLATED)
    val addCategoryEventChannel = _addCategoryEventChannel.receiveAsFlow()

    private val _addImageEventChannel = Channel<Unit>(Channel.CONFLATED)
    val addImageEventChannel = _addImageEventChannel.receiveAsFlow()


    var categoryList = MutableStateFlow(listOf<UserEntity>())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO){
            categoryList.value = userDataBase.userDao().getCategory()
        }
    }

    private val _addDataBaseCreatedChannel = Channel<Unit>(Channel.CONFLATED)
    val addDataBaseCreatedChannel = _addDataBaseCreatedChannel.receiveAsFlow()

    val showImage = MutableStateFlow(false)
    val categoryName = MutableStateFlow("")
    val categoryImagePath = MutableStateFlow("")

    fun onCategoryAddButtonClicked() {
        _addCategoryEventChannel.trySend(Unit)
    }

    fun onBrowseButtonClicked() {
        _addImageEventChannel.trySend(Unit)
    }

    fun onSubmitButtonClicked() {
        viewModelScope.launch {
            userDataBase.userDao().addCategory(
                UserEntity(
                    categoryName = categoryName.value,
                    categoryImagePath = categoryImagePath.value
                )
            )
            _addDataBaseCreatedChannel.trySend(Unit)
        }
    }

}