package ru.shurikus.design_system_app.screens.menu

import androidx.lifecycle.ViewModel
import ru.shurikus.corePreferences.repositories.ThemeSettingsRepository

class MenuViewModel constructor(
    private val themeSettingsRepository: ThemeSettingsRepository,
) : ViewModel() {

    fun onSwitchTheme() {
        themeSettingsRepository.isDarkTheme = !themeSettingsRepository.isDarkTheme
    }
}