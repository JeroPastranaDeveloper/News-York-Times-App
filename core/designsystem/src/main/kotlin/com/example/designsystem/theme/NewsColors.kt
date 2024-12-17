package com.example.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.core.designsystem.R

@Immutable
data class NewsColors(
    val primary: Color,
    val black: Color,
    val white: Color,
    val orange: Color,
    val green: Color,
    val blue: Color,
    val grey: Color
) {

    companion object {
        @Composable
        fun defaultDarkColors(): NewsColors = NewsColors(
            primary = colorResource(id = R.color.colorPrimary),
            white = colorResource(id = R.color.white),
            black = colorResource(R.color.dark),
            orange = colorResource(id = R.color.orange),
            green = colorResource(id = R.color.green),
            blue = colorResource(id = R.color.blue),
            grey = colorResource(id = R.color.grey)
        )

        @Composable
        fun defaultLightColors(): NewsColors = NewsColors(
            primary = colorResource(id = R.color.colorPrimary),
            white = colorResource(id = R.color.white),
            black = colorResource(id = R.color.dark),
            orange = colorResource(id = R.color.orange),
            green = colorResource(id = R.color.green),
            blue = colorResource(id = R.color.blue),
            grey = colorResource(id = R.color.grey)
        )
    }
}
