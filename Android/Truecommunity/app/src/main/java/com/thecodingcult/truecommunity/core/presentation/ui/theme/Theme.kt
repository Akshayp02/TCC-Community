package com.thecodingcult.truecommunity.core.presentation.ui.theme

// Import colors from Color.kt
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = ColorPrimary,
    secondary = colorSecondary,
    background = LightbackgroundColor,
    onBackground = ColorPrimary,
    onPrimary = TextColor,
    onSecondary = TextbodyColor,
    error = ErrorColor,
    onError = Color.White,
    surface = buttonGrayshade3Bg,
    onSurface = Color.White


)

private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,
    secondary = colorSecondary,
    background = LightbackgroundColor,
    onBackground = ColorPrimary,
    onPrimary = TextColor,
    onSecondary = TextbodyColor,

    error = ErrorColor,
    onError = Color.White,
    surface = buttonGrayshade3Bg,
    onSurface = Color.Black
)

fun getOrangeGradientBackgroundColor(): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF6935),
            Color(0xFFF84C10)
        )
    )
}

@Composable
fun TrueCommunityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
