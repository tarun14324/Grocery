package com.example.grocery.category

import com.example.grocery.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor() : BaseViewModel() {

    private val _addCategoryEventChannel = Channel<Unit>(Channel.CONFLATED)
    val addCategoryEventChannel = _addCategoryEventChannel.receiveAsFlow()


    fun onCategoryAddButtonClicked() {
        _addCategoryEventChannel.trySend(Unit)
    }

}