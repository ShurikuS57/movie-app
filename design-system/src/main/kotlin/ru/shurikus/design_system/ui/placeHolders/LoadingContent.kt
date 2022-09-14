package ru.shurikus.design_system.ui.placeHolders

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ru.shurikus.core.models.ErrorEntity
import ru.shurikus.core.models.ErrorType

data class LoadingContentParams(
    val loading: Boolean,
    val error: ErrorEntity?,
    val showPlaceHolder: Boolean,
)

@Composable
fun LoadingContent(
    params: LoadingContentParams,
    scaffoldState: ScaffoldState,
    content: @Composable () -> Unit,
) {
    if (params.error != null) {
        if (params.showPlaceHolder) {
            when (params.error.errorType) {
                ErrorType.Connection -> ErrorInternetConnectionPlaceHolder()
                ErrorType.Response, ErrorType.UNKNOWN, ErrorType.Validation -> ErrorLoadPlaceHolder()
            }
        } else {
            LaunchedEffect(scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "${params.error.messageTitle} ${params.error.message}"
                )
            }
            content()
        }
    } else if (params.loading) {
        LoadingPlaceHolder()
    } else {
        content()
    }
}