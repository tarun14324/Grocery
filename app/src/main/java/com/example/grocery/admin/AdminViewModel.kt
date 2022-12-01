package com.example.grocery.admin

import com.example.grocery.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor() : BaseViewModel() {
    private val _categoryEventChannel = Channel<Unit>(Channel.CONFLATED)
    val categoryEventChannel = _categoryEventChannel.receiveAsFlow()

    private val _categoryListEventChannel = Channel<Unit>(Channel.CONFLATED)
    val categoryListEventChannel = _categoryListEventChannel.receiveAsFlow()
    fun onCategoryButtonClicked() {
        _categoryEventChannel.trySend(Unit)
    }

    fun onCategoryListButtonClicked() {
        _categoryListEventChannel.trySend(Unit)
    }
}