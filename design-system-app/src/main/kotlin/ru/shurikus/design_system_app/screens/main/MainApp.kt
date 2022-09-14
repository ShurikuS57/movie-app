package ru.shurikus.design_system_app.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.compose.rememberNavController
import ru.shurikus.design_system.ui.components.MovieAppTheme
import ru.shurikus.design_system_app.navigation.DesignSystemNavHost

@Composable
fun MainApp(theme: State<Boolean>) {
    val navController = rememberNavController()
    MovieAppTheme(theme.value) {
        DesignSystemNavHost(navController = navController)
    }
}