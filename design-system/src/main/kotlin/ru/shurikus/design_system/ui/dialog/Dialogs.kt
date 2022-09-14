package ru.shurikus.design_system.ui.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun YesNoAlertDialog(
    isShowDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    title: String = "",
    message: String = "",
    positiveButtonText: String = "Ok",
    negativeButtonText: String = "",
    positiveButtonClick: (() -> Unit)? = null,
    negativeButtonClick: (() -> Unit)? = null,
) {
    if (isShowDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = message)
            },
            confirmButton = {
                Button(
                    onClick = {
                        positiveButtonClick?.invoke()
                        setShowDialog(false)
                    }) {
                    Text(positiveButtonText)
                }
            },
            dismissButton = {
                if (negativeButtonText.isNotBlank()) {
                    Button(
                        onClick = {
                            negativeButtonClick?.invoke()
                            setShowDialog(false)
                        }) {
                        Text(negativeButtonText)
                    }
                }
            }
        )
    }
}
