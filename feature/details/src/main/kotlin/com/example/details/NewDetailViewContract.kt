package com.example.details

import com.example.model.NewDetail
import com.example.viewmodel.BaseViewContract

class NewDetailViewContract : BaseViewContract() {
    data class UiState(
        val new: NewDetail = NewDetail(),
        val showShareDialog: Boolean = false,
        val showQrShareDialog: Boolean = false
    )

    sealed class UiIntent {
        data object OnFavoritePressed : UiIntent()
        data object OnSharePressed : UiIntent()
        data object CloseShareDialog : UiIntent()
        data object OnQrSharePressed : UiIntent()
    }
}
