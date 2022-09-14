package ru.shurikus.design_system.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.shurikus.design_system.ui.theme.*


object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val typography: Typography
        get() = AppTypography
}


@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit)
{
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.uiBackground
        )
    }

    ProvideAppColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme, colors),
            typography = AppTypography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
private fun ProvideAppColors(
    colors: AppColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalAppColors provides colorPalette, content = content)
}


private val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColorPalette provided")
}