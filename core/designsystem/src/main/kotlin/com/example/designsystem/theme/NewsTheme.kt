package com.example.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier


private val LocalColors = compositionLocalOf<NewsColors> {
    error("No colors provided! Make sure to wrap all usages of News components in NewsTheme.")
}

@Composable
fun NewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: NewsColors = if (darkTheme) {
        NewsColors.defaultDarkColors()
    } else {
        NewsColors.defaultLightColors()
    },
    background: NewsBackground = NewsBackground.defaultBackground(darkTheme),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalBackgroundTheme provides background,
    ) {
        Box(
            modifier = Modifier.background(background.color)
        ) {
            content()
        }
    }
}

/**
 * Contains ease-of-use accessors for different properties used to style and customize the app
 * look and feel.
 */

object NewsTheme {
    val colors: NewsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val background: NewsBackground
        @Composable
        @ReadOnlyComposable
        get() = LocalBackgroundTheme.current
}
