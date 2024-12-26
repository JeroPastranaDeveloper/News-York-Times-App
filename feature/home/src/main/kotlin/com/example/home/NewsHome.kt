package com.example.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.designsystem.components.NewsAppBar
import com.example.core.designsystem.R
import com.example.designsystem.theme.NewsTheme
import com.example.home.HomeViewContract.UiState
import com.example.home.HomeViewContract.UiIntent
import com.example.home.composables.NewCard
import com.example.model.ALL_NEWS
import com.example.model.FAVORITE_NEWS
import com.example.model.New
import com.example.navigation.NewsScreen
import com.example.navigation.currentComposeNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

@Composable
fun SharedTransitionScope.NewsHome(
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: HomeViewModel = koinViewModel()
) {
    SetStatusBarIconsColor()

    val state by viewModel.state.collectAsState(UiState())
    val composeNavigator = currentComposeNavigator

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (state.screen == FAVORITE_NEWS) {
                    viewModel.sendIntent(UiIntent.ChangeScreen(FAVORITE_NEWS))
                }
            }
        }
        val lifecycle = lifecycleOwner.value.lifecycle
        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = { NewsAppBar() },
        bottomBar = {
            NewsBottomBar(
                onAllNewsClick = {
                    viewModel.sendIntent(
                        UiIntent.ChangeScreen(ALL_NEWS)
                    )
                },
                onFavoriteNewsClick = {
                    viewModel.sendIntent(
                        UiIntent.ChangeScreen(FAVORITE_NEWS)
                    )
                }
            )
        }
    ) { paddingValues ->
        when (state.screen) {
            ALL_NEWS -> {
                if (state.isLoading) {
                    LoadingIndicator()
                } else {
                    NewsGrid(
                        animatedVisibilityScope = animatedVisibilityScope,
                        news = state.news,
                        paddingValues = paddingValues,
                        onCardClick = { newsItem ->
                            composeNavigator.navigate(NewsScreen.Details(newsItem))
                        }
                    )
                }
            }

            else -> {
                if (state.news.isEmpty()) {
                    EmptyStateMessage(message = stringResource(R.string.empty_favorites))
                } else {
                    NewsGrid(
                        animatedVisibilityScope = animatedVisibilityScope,
                        news = state.news,
                        paddingValues = paddingValues,
                        onCardClick = { newsItem ->
                            composeNavigator.navigate(NewsScreen.Details(newsItem))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SharedTransitionScope.NewsGrid(
    animatedVisibilityScope: AnimatedVisibilityScope,
    news: List<New>,
    paddingValues: PaddingValues,
    onCardClick: (New) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(news.size) { index ->
                NewCard(animatedVisibilityScope, news[index]) {
                    onCardClick(news[index])
                }
            }
        }
    )
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = NewsTheme.colors.black
        )
    }
}

@Composable
fun EmptyStateMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}

@Composable
private fun SetStatusBarIconsColor() {

    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = false
    )
}

@Composable
fun NewsBottomBar(onAllNewsClick: () -> Unit, onFavoriteNewsClick: () -> Unit) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.all_news)) },
            selected = false,
            onClick = { onAllNewsClick() }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
            label = { Text(stringResource(R.string.favorite_news)) },
            selected = false,
            onClick = { onFavoriteNewsClick() }
        )
    }
}
