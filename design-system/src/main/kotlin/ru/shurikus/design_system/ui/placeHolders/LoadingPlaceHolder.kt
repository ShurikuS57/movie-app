package ru.shurikus.design_system.ui.placeHolders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.shurikus.design_system.ui.components.AppTheme
import ru.shurikus.design_system.ui.components.MovieAppTheme

@Composable
fun LoadingPlaceHolder() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(AppTheme.colors.uiBackground)
    ) {
        CircularProgressIndicator(color = AppTheme.colors.onSurface)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenViewPreview() {
    MovieAppTheme {
        LoadingPlaceHolder()
    }
}
