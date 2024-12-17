package com.example.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModelWithIntent<S, I> : BaseViewModel<S>(), ViewModelIntentSender<I> {

    private val vmIntent = Channel<I>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            vmIntent.consumeAsFlow().collect { intent ->
                manageIntent(intent)
            }
        }
    }

    protected abstract suspend fun manageIntent(intent: I)

    override fun sendIntent(intent: I) {
        viewModelScope.launch {
            vmIntent.send(intent)
        }
    }
}