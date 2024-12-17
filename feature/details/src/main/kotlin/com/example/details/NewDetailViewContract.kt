package com.example.details

import com.example.model.NewDetail
import com.example.viewmodel.BaseViewContract

class NewDetailViewContract : BaseViewContract() {
    data class UiState(
        val isLoading: Boolean = false,
        val new: NewDetail = NewDetail()
    )

    sealed class UiIntent {
        data object OnFavoritePressed : UiIntent()
    }
}
