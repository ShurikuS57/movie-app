package ru.shurikus.design_system_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.shurikus.design_system_app.screens.buttons.ButtonsScreen
import ru.shurikus.design_system_app.screens.inputText.InputTextScreen
import ru.shurikus.design_system_app.screens.menu.MenuScreen

@Composable
fun DesignSystemNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = DesignSystemRoute.MainMenu.route
    ) {
        composable(DesignSystemRoute.MainMenu.route) {
            MenuScreen(navController)
        }
        composable(DesignSystemRoute.ActionButtons.route) {
            ButtonsScreen(navController)
        }

        composable(DesignSystemRoute.InputText.route) {
            InputTextScreen(navController)
        }
    }
}
