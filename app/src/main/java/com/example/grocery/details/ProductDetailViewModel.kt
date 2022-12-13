package com.example.grocery.details

import com.example.grocery.base.BaseViewModel
import com.example.grocery.room.UserDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val dataBase: UserDataBase) :
    BaseViewModel() {

    val itemQuantity = MutableStateFlow(0)
    private val _addLayoutEventChannel = Channel<Unit>(Channel.CONFLATED)
    val addLayoutEventChannel = _addLayoutEventChannel.receiveAsFlow()

    private val _addEventChannel = Channel<Unit>(Channel.CONFLATED)
    val addEventChannel = _addEventChannel.receiveAsFlow()

    private val _removeEventChannel = Channel<Unit>(Channel.CONFLATED)
    val removeEventChannel = _removeEventChannel.receiveAsFlow()
    override fun refreshData() {
        //  TODO("Not yet implemented")
    }

    fun onAddLayoutClicked() {
        _addLayoutEventChannel.trySend(Unit)
    }

    fun onAddClicked() {
        _addEventChannel.trySend(Unit)
    }

    fun onRemoveClicked() {
        _removeEventChannel.trySend(Unit)
    }
}