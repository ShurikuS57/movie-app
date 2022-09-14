package ru.shurikus.design_system_app.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import org.koin.android.ext.android.inject
import ru.shurikus.corePreferences.repositories.ThemeSettingsRepository

class MainActivity : ComponentActivity() {
    private val themeSettingsRepository: ThemeSettingsRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme = themeSettingsRepository.themeState.collectAsState()
            MainApp(theme)
        }
    }
}