package com.example.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.designsystem.theme.NewsTheme
import com.kmpalette.palette.graphics.Palette

@Composable
internal fun Palette?.paletteBackgroundBrush(): State<Pair<Brush, Color>> {
    val defaultBackground = NewsTheme.colors.white
    val defaultTextColor = Color.Black

    return remember(this) {
        derivedStateOf {
            val light = this?.lightVibrantSwatch?.rgb
            val domain = this?.dominantSwatch?.rgb

            val brush: Brush
            val textColor: Color

            if (domain != null) {
                val domainColor = Color(domain)
                textColor = if (isColorDark(domainColor)) Color.White else Color.Black

                brush = if (light != null) {
                    val lightColor = Color(light)
                    val gradient = arrayOf(
                        0.0f to domainColor,
                        1f to lightColor,
                    )
                    Brush.verticalGradient(colorStops = gradient)
                } else {
                    Brush.linearGradient(colors = listOf(domainColor, domainColor))
                }
            } else {
                brush = Brush.linearGradient(colors = listOf(defaultBackground, defaultBackground))
                textColor = defaultTextColor
            }

            brush to textColor
        }
    }
}

/**
 * Determines if a given color is dark.
 * @param color The color to evaluate.
 * @return True if the color is dark, false otherwise.
 */
private fun isColorDark(color: Color): Boolean {
    val darkness = (0.299 * color.red + 0.587 * color.green + 0.114 * color.blue)
    return darkness < 0.5
}
