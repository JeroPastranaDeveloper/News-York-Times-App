package com.example.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.details.NewDetailViewContract.UiIntent
import com.example.details.NewDetailViewContract.UiState
import com.example.domain.repository.details.DetailsRepository
import com.example.domain.repository.roomdatabase.DeleteFavoriteNew
import com.example.domain.repository.roomdatabase.SaveNewRepository
import com.example.model.New
import com.example.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NewDetailViewModel(
    private val detailRepository: DetailsRepository,
    private val saveNewRepository: SaveNewRepository,
    private val deleteFavoriteNew: DeleteFavoriteNew,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<UiState, UiIntent>() {

    override val initialViewState = UiState()
    override suspend fun manageIntent(intent: UiIntent) {
        when (intent) {
            UiIntent.OnFavoritePressed -> handleFavoriteAction()
            UiIntent.OnSharePressed -> setState{ copy(showShareDialog = true) }
            UiIntent.CloseShareDialog -> setState { copy(showShareDialog = false) }
            UiIntent.OnQrSharePressed -> setState { copy(showQrShareDialog = true) }
        }
    }

    private val newFlow: StateFlow<New?> = savedStateHandle.getStateFlow("new", null)

    init {
        observeNewDetails()
    }

    private fun observeNewDetails() {
        newFlow
            .filterNotNull()
            .filter { it.articleUrl.isNotEmpty() }
            .flatMapLatest { detailRepository.fetchNewDetail(it.articleUrl, it.imageUrl) }
            .onEach { newDetail ->
                setState { copy(new = newDetail) }
            }
            .launchIn(viewModelScope)
    }

    private fun handleFavoriteAction() {
        val currentNew = state.value.new
        if (currentNew.isFavorite) {
            updateFavoriteStatus(false) { deleteFavoriteNew.deleteFavoriteNew(currentNew.webUrl) }
        } else {
            updateFavoriteStatus(true) {
                saveNewRepository.saveNew(currentNew)
            }
        }
    }

    private fun updateFavoriteStatus(isFavorite: Boolean, action: suspend () -> Unit) {
        viewModelScope.launch {
            action()
            setState {
                copy(new = state.value.new.copy(isFavorite = isFavorite))
            }
        }
    }
}
