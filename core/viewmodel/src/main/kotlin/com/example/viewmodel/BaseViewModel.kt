package com.example.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<S> : ViewModel() {

    private val _state: MutableStateFlow<S> by lazy { MutableStateFlow(initialViewState) }
    val state: StateFlow<S> get() = _state

    protected abstract val initialViewState: S

    protected fun setState(newState: S.() -> S) {
        _state.update(newState)
    }
}
