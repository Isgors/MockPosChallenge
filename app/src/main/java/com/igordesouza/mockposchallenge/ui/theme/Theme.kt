package com.igordesouza.mockposchallenge.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
private fun getAppDarkColorScheme() = darkColorScheme(
    primary = flavorPrimaryDark(),
    secondary = flavorSecondaryDark(),
    tertiary = flavorTertiaryDark(),
)

@Composable
private fun getAppLightColorScheme() = lightColorScheme(
    primary = flavorPrimaryLight(),
    secondary = flavorSecondaryLight(),
    tertiary = flavorTertiaryLight(),
)

@Composable
fun MockPosChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> getAppDarkColorScheme()
        else -> getAppLightColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}