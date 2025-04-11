package com.example.newsapi.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.designsystem.theme.NewsTheme
import com.example.navigation.AppComposeNavigator
import com.example.navigation.NewsScreen
import com.example.newsapi.navigation.NewsNavHost

@Composable
fun NewsMain(composeNavigator: AppComposeNavigator<NewsScreen>) {
    NewsTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        NewsNavHost(navHostController = navHostController)
    }
}
