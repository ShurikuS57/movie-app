package ru.shurikus.design_system.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

internal val DarkColorPalette = AppColors(
    primary = BackgroundDark,
    onPrimary = Gray500,
    onSurface = OrangeFF,
    uiBackground = BackgroundDark,
    actionButtonBackground = OrangeFF,
    disableActionButtonBackground = Orange9C,
    starSelected = StarSelectedDark,
    starUnelected = StarUnselectedDark,
    icon = IconColorDark,
    iconButtonBackground = IconBackgroundDark,
    boxGradientStart = GradientBoxStartDark,
    boxGradientEnd = GradientBoxEndDark,
    textH4 = Color.White,
    textH5 = Color.White,
    textButton = BackgroundDark,
    disableTextButton = BackgroundDark2A,
    error = ErrorColor,
    isDark = true
)

internal val LightColorPalette = AppColors(
    primary = Color.White,
    onPrimary = Color.Black,
    onSurface = OrangeFF,
    uiBackground = Color.White,
    actionButtonBackground = OrangeFF,
    disableActionButtonBackground = Orange9C,
    starSelected = StarSelected,
    starUnelected = StarUnselected,
    icon = IconColor,
    iconButtonBackground = IconBackground,
    boxGradientStart = GradientBoxStart,
    boxGradientEnd = GradientBoxEnd,
    textH4 = Color.Black,
    textH5 = Color.Black,
    textButton = BackgroundDark,
    disableTextButton = BackgroundDark2A,
    error = ErrorColor,
    isDark = false
)

@Stable
class AppColors(
    primary: Color,
    onPrimary: Color, // Input text
    onSurface: Color, // Title and input icons
    uiBackground: Color,
    actionButtonBackground: Color,
    disableActionButtonBackground: Color,
    textButton: Color,
    disableTextButton: Color,
    starSelected: Color,
    starUnelected: Color,
    boxGradientStart: Color,
    boxGradientEnd: Color,
    icon: Color,
    iconButtonBackground: Color,
    textH4: Color,
    textH5: Color,
    error: Color,
    isDark: Boolean,
) {
    var primary by mutableStateOf(uiBackground)
        private set

    var onPrimary by mutableStateOf(onPrimary)
        private set

    var onSurface by mutableStateOf(onSurface)
        private set

    var uiBackground by mutableStateOf(primary)
        private set

    var actionButtonBackground by mutableStateOf(actionButtonBackground)
        private set

    var disableActionButtonBackground by mutableStateOf(disableActionButtonBackground)
        private set

    var starSelected by mutableStateOf(starSelected)
        private set

    var starUnelected by mutableStateOf(starUnelected)
        private set

    var icon by mutableStateOf(icon)
        private set

    var iconButtonBackground by mutableStateOf(iconButtonBackground)
        private set

    var boxGradientStart by mutableStateOf(boxGradientStart)
        private set
    var boxGradientEnd by mutableStateOf(boxGradientEnd)
        private set

    var textH4 by mutableStateOf(textH4)
        private set

    var textH5 by mutableStateOf(textH5)

    var textButton by mutableStateOf(textButton)
        private set

    var disableTextButton by mutableStateOf(disableTextButton)
        private set

    var error by mutableStateOf(error)
        private set

    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: AppColors) {
        primary = other.primary
        onPrimary = other.onPrimary
        onSurface = other.onSurface
        uiBackground = other.uiBackground
        actionButtonBackground = other.actionButtonBackground
        disableActionButtonBackground = other.disableActionButtonBackground
        starSelected = other.starSelected
        starUnelected = other.starUnelected
        icon = other.icon
        iconButtonBackground = other.iconButtonBackground
        boxGradientStart = other.boxGradientStart
        boxGradientEnd = other.boxGradientEnd
        textH4 = other.textH4
        textH5 = other.textH5
        textButton = other.textButton
        disableTextButton = other.disableTextButton
        error = other.error
        isDark = other.isDark
    }

    fun copy(): AppColors = AppColors(
        primary = primary,
        onPrimary = onPrimary,
        onSurface = onSurface,
        uiBackground = uiBackground,
        actionButtonBackground = actionButtonBackground,
        disableActionButtonBackground = disableActionButtonBackground,
        starSelected = starSelected,
        starUnelected = starUnelected,
        icon = icon,
        iconButtonBackground = iconButtonBackground,
        boxGradientStart = boxGradientStart,
        boxGradientEnd = boxGradientEnd,
        textH4 = textH4,
        textH5 = textH5,
        textButton = textButton,
        disableTextButton = disableTextButton,
        error = error,
        isDark = isDark,
    )
}

internal fun debugColors(
    darkTheme: Boolean,
    appColors: AppColors,
    debugColor: Color = Color.Magenta,
) = Colors(
    primary = appColors.primary,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = appColors.uiBackground,
    surface = appColors.uiBackground,
    error = debugColor,
    onPrimary = appColors.onPrimary,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = appColors.onSurface,
    onError = appColors.error,
    isLight = !darkTheme
)
