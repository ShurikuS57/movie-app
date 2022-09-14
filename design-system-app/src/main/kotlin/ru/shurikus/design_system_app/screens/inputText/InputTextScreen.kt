package ru.shurikus.design_system_app.screens.inputText

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.get
import ru.shurikus.design_system.ui.components.AppTheme
import ru.shurikus.design_system.ui.components.MovieAppTheme
import ru.shurikus.design_system.ui.components.TextInputField
import ru.shurikus.design_system_app.screens.menu.MenuViewModel

@Composable
fun InputTextScreen(navController: NavHostController) {
    val viewModel: MenuViewModel = get()
    InputTextScreenContent(navController) {
        viewModel.onSwitchTheme()
    }
}

@Composable
private fun InputTextScreenContent(
    navController: NavHostController,
    onSwitchThemeCallback: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Input text")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Input text")
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
            var inputText by remember { mutableStateOf("") }
            TextInputField(textValue = inputText, textLabel = "Label", onTextChange = {
                inputText = it
            })
            Spacer(modifier = Modifier.height(14.dp))
            var inputText2 by remember { mutableStateOf("") }
            TextInputField(
                textValue = inputText2,
                textLabel = "Label",
                leadingIcon = Icons.Default.Lock,
                onTextChange = {
                    inputText2 = it
                }
            )
        }
    }
}

@Preview
@Composable
fun InputTextScreenPreview() {
    MovieAppTheme {
        InputTextScreenContent(rememberNavController()) {}
    }
}

@Preview
@Composable
fun InputTextScreenDarkPreview() {
    MovieAppTheme(darkTheme = true) {
        InputTextScreenContent(rememberNavController()) {}
    }
}