package ru.shurikus.app_ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.shurikus.app_ui.ui.login.LoginScreen

@Composable
internal fun LoginNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = LoginRoute.Login.route
    ) {
        composable(LoginRoute.Login.route) {
            LoginScreen()
        }
    }
}
