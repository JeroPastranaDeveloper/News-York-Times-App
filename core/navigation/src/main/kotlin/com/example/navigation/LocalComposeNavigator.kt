package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

public val LocalComposeNavigator: ProvidableCompositionLocal<AppComposeNavigator<NewsScreen>> =
    compositionLocalOf { error("No AppComposeNavigator provided!") }

/**
 * Retrieves the current [AppComposeNavigator] at the call site's position in the hierarchy.
 */
val currentComposeNavigator: AppComposeNavigator<NewsScreen>
    @Composable
    @ReadOnlyComposable
    get() = LocalComposeNavigator.current
