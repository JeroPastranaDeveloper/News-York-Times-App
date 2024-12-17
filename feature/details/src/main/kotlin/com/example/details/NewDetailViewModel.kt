package com.example.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.details.NewDetailViewContract.UiIntent
import com.example.details.NewDetailViewContract.UiState
import com.example.domain.repository.details.DetailsRepository
import com.example.domain.repository.roomdatabase.DeleteFavoriteNew
import com.example.domain.repository.roomdatabase.SaveNewRepository
import com.example.model.New
import com.example.viewmodel.BaseViewModelWithIntent
import kotlinx.coroutines.flow.StateFlow
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
) : BaseViewModelWithIntent<UiState, UiIntent>() {

    override val initialViewState = UiState()
    override suspend fun manageIntent(intent: UiIntent) {
        when (intent) {
            UiIntent.OnFavoritePressed -> onFavoritePressed()
        }
    }

    private val newFlow: StateFlow<New?> = savedStateHandle.getStateFlow("new", null)

    init {
        observeNewDetail()
    }

    private fun observeNewDetail() {
        newFlow.filterNotNull()
            .flatMapLatest {
                detailRepository.fetchNewsList(it.articleUrl, it.imageUrl)
            }.onEach { newDetail ->
                setState {
                    copy(new = newDetail)
                }
            }.launchIn(viewModelScope)
    }

    private fun onFavoritePressed() {
        if (state.value.new.isFavorite) deleteFavorite()
        else saveNewAsFavorite()
    }

    private fun saveNewAsFavorite() {
        viewModelScope.launch {
            val title = newFlow.value?.title.orEmpty()
            val articleUrl = newFlow.value?.articleUrl.orEmpty()
            saveNewRepository.saveNew(state.value.new.copy(title = title, webUrl = articleUrl))
            setState {
                copy(new = state.value.new.copy(isFavorite = true))
            }
        }
    }

    private fun deleteFavorite() {
        viewModelScope.launch {
            deleteFavoriteNew.deleteFavoriteNew(state.value.new.webUrl)
            setState { copy(new = state.value.new.copy(isFavorite = false)) }
        }
    }
}
