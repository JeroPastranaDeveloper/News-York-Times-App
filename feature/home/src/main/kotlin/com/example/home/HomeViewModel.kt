package com.example.home

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.home.HomeRepository
import com.example.domain.repository.roomdatabase.GetNewsRepository
import com.example.home.HomeViewContract.UiIntent
import com.example.home.HomeViewContract.UiState
import com.example.model.ALL_NEWS
import com.example.model.FAVORITE_NEWS
import com.example.viewmodel.BaseViewModelWithIntent
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsRepository: HomeRepository,
    private val getNewsRepository: GetNewsRepository,
) : BaseViewModelWithIntent<UiState, UiIntent>() {

    override val initialViewState = UiState()

    override suspend fun manageIntent(intent: UiIntent) {
        when (intent) {
            is UiIntent.ChangeScreen -> changeScreen(intent.screen)
        }
    }

    init {
        changeScreen(ALL_NEWS)
    }

    private fun getAllNews() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            newsRepository.fetchNewsList().collect { news ->
                setState {
                    UiState(
                        screen = ALL_NEWS,
                        news = news.fixNoImage(),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getFavoriteNews() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            val favoriteNews = getNewsRepository.getNews().map {
                it.toNew()
            }
            setState {
                copy(screen = FAVORITE_NEWS, news = favoriteNews, isLoading = false)
            }
        }
    }

    private fun changeScreen(screen: Int) {
        if (screen == ALL_NEWS) getAllNews() else getFavoriteNews()
    }
}
