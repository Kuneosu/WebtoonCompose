package com.kuneosu.newcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    background = Color.Black,
    // Setting Screen Background Color
    onBackground = Color.Black,
    // Setting Screen Button Color
    onPrimary = Color(0xFF212121),

    // Setting Screen TextField Color
    surfaceContainer = Color(0xff161616),
    // Setting Screen TextField Text Color
    onSurface = Color(0xff555555),

    // Setting Screen Selected Button Color
    onSecondaryContainer = Color(0xff212121),
    // Setting Screen Unselected Button Color
    secondaryContainer = Color(0xff161616),
    // Setting Screen Unselected Button Text Color
    secondary = Color(0xff555555),

    // Main Tab row Unselected Button color
    tertiaryContainer = Color(0xff1b1b1b),
    // Main Tab row Unselected Button Text color
    tertiary = Color(0xff5e5e5e),
    // Main Tab row Border color
    onTertiaryContainer = Color(0xff1b1b1b)
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    background = Color.White,
    // Setting Screen Background Color
    onBackground = Color(0xFFf2f2f2),
    // Setting Screen Button Color
    onPrimary = Color.White,

    // Setting Screen TextField Color
    surfaceContainer = Color(0xffe7e7e7),
    // Setting Screen TextField Text Color
    onSurface = Color(0xff9c9c9c),

    // Setting Screen Selected Button Color
    onSecondaryContainer = Color(0xffffffff),
    // Setting Screen Unselected Button Color
    secondaryContainer = Color(0xfff8f8f8),
    // Setting Screen Unselected Button Text Color
    secondary = Color(0xffababab),

    // Main Tab row Unselected Button color
    tertiaryContainer = Color(0xffffffff),
    // Main Tab row Unselected Button Text color
    tertiary = Color(0xff989898),
    // Main Tab row Border color
    onTertiaryContainer = Color(0xff989898)
)

enum class ThemeMode {
    LIGHT, DARK, SYSTEM
}

@Composable
fun NewComposeTheme(
    themeMode: ThemeMode,
    content: @Composable () -> Unit
) {
    val colors = when (themeMode) {
        ThemeMode.DARK -> DarkColorScheme
        ThemeMode.LIGHT -> LightColorScheme
        ThemeMode.SYSTEM -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}