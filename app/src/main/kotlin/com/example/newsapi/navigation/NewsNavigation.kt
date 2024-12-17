package com.example.newsapi.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.details.NewDetailScreen
import com.example.home.NewsHome
import com.example.navigation.NewsScreen

context(SharedTransitionScope)
fun NavGraphBuilder.newsNavigation() {
    composable<NewsScreen.Home> {
        NewsHome(this)
    }

    composable<NewsScreen.Details>(
        typeMap = NewsScreen.Details.typeMap
    ) {
        NewDetailScreen(this)
    }
}