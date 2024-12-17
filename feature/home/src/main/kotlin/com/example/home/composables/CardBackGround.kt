package com.example.home.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.designsystem.theme.NewsTheme
import com.kmpalette.palette.graphics.Palette

@Composable
internal fun Palette?.paletteBackgroundColor(): State<Pair<Color, Color>> {
    val defaultBackground = NewsTheme.colors.grey
    return remember(this) {
        derivedStateOf {
            val rgb = this?.dominantSwatch?.rgb
            val backgroundColor = if (rgb != null) Color(rgb) else defaultBackground
            val textColor = if (isColorDark(backgroundColor)) Color.White else Color.Black
            backgroundColor to textColor
        }
    }
}

private fun isColorDark(color: Color): Boolean {
    val luminance = (0.299 * color.red + 0.587 * color.green + 0.114 * color.blue)
    return luminance < 0.7
}