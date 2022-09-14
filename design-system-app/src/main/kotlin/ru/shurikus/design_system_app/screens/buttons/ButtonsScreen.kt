package ru.shurikus.design_system_app.screens.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.inject
import ru.shurikus.design_system.ui.components.ActionButton
import ru.shurikus.design_system.ui.components.ActionButtonType
import ru.shurikus.design_system.ui.components.AppTheme
import ru.shurikus.design_system.ui.components.MovieAppTheme
import ru.shurikus.design_system_app.screens.menu.MenuViewModel

@Composable
fun ButtonsScreen(navController: NavHostController) {
    val viewModel: MenuViewModel by inject()
    ButtonsScreenContent(navController) {
        viewModel.onSwitchTheme()
    }
}

@Composable
private fun ButtonsScreenContent(
    navController: NavHostController,
    onSwitchThemeCallback: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Buttons")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Buttons")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onSwitchThemeCallback.invoke()
                    }) {
                        Icon(Icons.Filled.DarkMode, null, tint = AppTheme.colors.icon)
                    }
                },
            )
        }
    ) {
        Column(Modifier.padding(all = 16.dp)) {
            Spacer(modifier = Modifier.height(6.dp))
            ActionButton(text = "Large button", onClick = {}) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "Small button", type = ActionButtonType.SMALL, onClick = {}) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "Text only button",
                type = ActionButtonType.ONLY_TEXT,
                onClick = {}) {

            }

            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "Text only button",
                type = ActionButtonType.ONLY_TEXT,
                progress = true,
                onClick = {}) {

            }

            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "Disable button",
                isEnable = false,
                type = ActionButtonType.SMALL,
                onClick = {}) {

            }

            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "Progress button. Press",
                progress = true,
                type = ActionButtonType.BIG, onClick = {}) {

            }

            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(text = "",
                type = ActionButtonType.SMALL, onClick = {}) {
                Icon(
                    Icons.Filled.ArrowBack,
                    null,
                    tint = Color.Black)
            }
        }
    }
}

@Preview
@Composable
fun ButtonsScreenPreview() {
    MovieAppTheme {
        ButtonsScreenContent(rememberNavController()) {}
    }
}

@Preview
@Composable
fun ButtonsScreenDarkPreview() {
    MovieAppTheme(darkTheme = true) {
        ButtonsScreenContent(rememberNavController()) {}
    }
}