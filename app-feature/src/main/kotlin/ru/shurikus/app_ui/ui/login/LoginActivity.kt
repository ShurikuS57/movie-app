package ru.shurikus.app_ui.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.inject
import ru.shurikus.app_ui.navigation.LoginNavHost
import ru.shurikus.corePreferences.repositories.ThemeSettingsRepository
import ru.shurikus.design_system.ui.components.MovieAppTheme

internal class LoginActivity : ComponentActivity() {
    private val themeSettingsRepository: ThemeSettingsRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme = themeSettingsRepository.themeState.collectAsState()
            LoginNavHost(theme)
        }
    }

    @Composable
    internal fun LoginNavHost(theme: State<Boolean>) {
        val navController = rememberNavController()
        MovieAppTheme(theme.value) {
            LoginNavHost(navController = navController)
        }
    }
}
