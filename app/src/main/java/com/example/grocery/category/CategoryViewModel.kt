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
    private val _toastEventChannel = Channel<String>(Channel.CONFLATED)
    val toastEventChannel = _toastEventChannel.receiveAsFlow()

    var categoryList = MutableStateFlow(listOf<UserEntity>())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryList.value = userDataBase.userDao().getCategory()
        }
    }

    private val _addDataBaseCreatedChannel = Channel<Unit>(Channel.CONFLATED)
    val addDataBaseCreatedChannel = _addDataBaseCreatedChannel.receiveAsFlow()

    val showImage = MutableStateFlow(false)
    val isUpdateCategory = MutableStateFlow(false)
    val categoryName = MutableStateFlow("")
    val categoryId = MutableStateFlow(0)
    val categoryImagePath = MutableStateFlow("")

    fun onCategoryAddButtonClicked() {
        _addCategoryEventChannel.trySend(Unit)
    }

    fun onBrowseButtonClicked() {
        _addImageEventChannel.trySend(Unit)
    }

    fun onSubmitButtonClicked() {
        viewModelScope.launch {
            try {
                if (isUpdateCategory.value) {
                    userDataBase.userDao().editCategory(
                        UserEntity(
                            id = categoryId.value,
                            categoryName = categoryName.value,
                            categoryImagePath = categoryImagePath.value
                        )
                    )
                    _toastEventChannel.trySend("successfully Updated")
                    _addDataBaseCreatedChannel.trySend(Unit)
                } else {
                    userDataBase.userDao().addCategory(
                        UserEntity(
                            categoryName = categoryName.value,
                            categoryImagePath = categoryImagePath.value
                        )
                    )
                    _toastEventChannel.trySend("successfully created")
                    _addDataBaseCreatedChannel.trySend(Unit)
                }
            } catch (e: Exception) {
                e.localizedMessage?.let { _toastEventChannel.trySend(it) }
            }
        }
    }
    override fun refreshData() {
       getData()
    }

}