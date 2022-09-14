package ru.shurikus.design_system_app.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import ru.shurikus.design_system.ui.components.AppTheme
import ru.shurikus.design_system.ui.components.MovieAppTheme
import ru.shurikus.design_system_app.R
import ru.shurikus.design_system_app.models.MenuUiItem
import ru.shurikus.design_system_app.navigation.DesignSystemRoute

private val menuData = listOf(
    MenuUiItem(
        name = "Action buttons",
        image = R.drawable.ic_menu_button,
        route = DesignSystemRoute.ActionButtons
    ),
    MenuUiItem(
        name = "Input text",
        image = R.drawable.ic_input_text,
        route = DesignSystemRoute.InputText
    ),
)

@Composable
fun MenuScreen(navController: NavHostController) {
    val viewModel: MenuViewModel = getViewModel()
    menuScreenContent(navController) {
        viewModel.onSwitchTheme()
    }
}

@Composable
private fun menuScreenContent(
    navController: NavHostController,
    onSwitchThemeCallback: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catalog") }, actions = {
                IconButton(onClick = {
                    onSwitchThemeCallback.invoke()
                }) {
                    Icon(Icons.Filled.DarkMode, null, tint = AppTheme.colors.icon)
                }
            })
        }
    ) { paddingValues ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
            color = AppTheme.colors.uiBackground) {
            ComponentMenu(menuData) {
                navController.navigate(it.route.route)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentMenu(data: List<MenuUiItem>, onClickMenuItem: (MenuUiItem) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(16.dp)) {
        items(data) {
            Card(
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.LightGray,
                modifier = Modifier.padding(8.dp),
                onClick = { onClickMenuItem.invoke(it) }
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = it.image),
                        contentDescription = "",
                        modifier = Modifier.height(42.dp)
                    )
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                    Text(text = it.name, style = AppTheme.typography.body1)
                }
            }
        }
    }
}

@Preview()
@Composable
fun MenuScreenPreview() {
    MovieAppTheme {
        menuScreenContent(navController = rememberNavController()) {}
    }
}

@Preview()
@Composable
fun MenuScreenDarkPreview() {
    MovieAppTheme(darkTheme = true) {
        menuScreenContent(navController = rememberNavController()) {}
    }
}