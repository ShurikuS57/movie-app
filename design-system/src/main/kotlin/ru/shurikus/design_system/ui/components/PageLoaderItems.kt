package ru.shurikus.design_system.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shurikus.design_system.ui.theme.AppTypography


@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = AppTheme.colors.onSurface)
    }
}

@Composable
fun LoadingItem() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(AppTheme.colors.uiBackground)
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(color = AppTheme.colors.onSurface)
    }
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = AppTypography.h6,
            color = AppTheme.colors.error
        )
        ActionButton(text = "Try again", type = ActionButtonType.SMALL, onClick = onClickRetry){

        }
    }
}

@Preview
@Composable
internal fun LoadingViewPreview() {
    MovieAppTheme {
        LoadingView()
    }
}

@Preview
@Composable
internal fun LoadingItemPreview() {
    MovieAppTheme {
        LoadingItem()
    }
}

@Preview
@Composable
internal fun ErrorItemPreview() {
    MovieAppTheme {
        ErrorItem(message = "Error load data") {}
    }
}

@Preview
@Composable
internal fun ErrorItemDarkPreview() {
    MovieAppTheme(darkTheme = true) {
        ErrorItem(message = "Error load data") {}
    }
}