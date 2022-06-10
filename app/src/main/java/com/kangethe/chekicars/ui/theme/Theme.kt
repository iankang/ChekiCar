package com.kangethe.chekicars.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Grey700,
    primaryVariant = Grey500,
    secondary = Mustard500,
    onSecondary = Black500,
    background = Grey200,
    onBackground = Black500,
    surface = Grey500,


)

private val LightColorPalette = lightColors(
    primary = Grey500,
    primaryVariant = Grey200,
    secondary = Mustard500,
    onSecondary = Black500,
    background = SubtleWhite500,
    onBackground = Black700,
    surface = Grey700,
    onSurface = Black700

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ChekiCarsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}