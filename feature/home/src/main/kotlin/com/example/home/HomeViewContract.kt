package com.example.home

import com.example.model.ALL_NEWS
import com.example.model.New
import com.example.viewmodel.BaseViewContract

class HomeViewContract : BaseViewContract() {
    data class UiState(
        val isLoading: Boolean = false,
        val news: List<New> = emptyList(),
        val screen: Int? = ALL_NEWS
    )

    sealed class UiIntent {
        data class ChangeScreen(val screen: Int) : UiIntent()
    }
}
