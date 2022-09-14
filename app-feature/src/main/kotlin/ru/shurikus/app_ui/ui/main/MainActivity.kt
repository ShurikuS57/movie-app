package ru.shurikus.app_ui.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityRetainedScope
import org.koin.core.scope.Scope
import ru.shurikus.app_ui.navigation.MainNavHost
import ru.shurikus.corePreferences.repositories.ThemeSettingsRepository
import ru.shurikus.design_system.ui.components.MovieAppTheme

internal class MainActivity : ComponentActivity(), AndroidScopeComponent {
    override val scope : Scope by activityRetainedScope()
    private val themeSettingsRepository: ThemeSettingsRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme = themeSettingsRepository.themeState.collectAsState()
            MainNavHost(theme)
        }
    }

    @Composable
    internal fun MainNavHost(theme: State<Boolean>) {
        val navController = rememberNavController()
        MovieAppTheme(theme.value) {
            MainNavHost(navController = navController)
        }
    }
}
