package com.example.grocery.home

import com.example.grocery.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    private val _adminEventChannel = Channel<Unit>(Channel.CONFLATED)
    val adminEventChannel = _adminEventChannel.receiveAsFlow()

    private val _userEventChannel = Channel<Unit>(Channel.CONFLATED)
    val userEventChannel = _userEventChannel.receiveAsFlow()
    fun onAdminButtonClicked() {
        _adminEventChannel.trySend(Unit)
    }

    fun onUserButtonClicked() {
        _userEventChannel.trySend(Unit)
    }

    override fun refreshData() {
        // TODO("Not yet implemented")
    }
}