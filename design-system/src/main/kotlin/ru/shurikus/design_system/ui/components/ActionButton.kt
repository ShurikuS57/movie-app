package ru.shurikus.design_system.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shurikus.design_system.extentions.conditional

enum class ActionButtonType {
    BIG,
    SMALL,
    ONLY_TEXT
}

@Composable
fun ActionButton(
    text: String,
    type: ActionButtonType = ActionButtonType.BIG,
    isEnable: Boolean = true,
    progress: Boolean = false,
    onClick: () -> Unit,
    content: @Composable () -> Unit?,
) {
    val height = ButtonDefaults.MinHeight
    val roundCorner = if (type == ActionButtonType.SMALL) {
        12.dp
    } else {
        16.dp
    }
    Button(
        onClick = onClick,
        enabled = !progress && isEnable,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        shape = RoundedCornerShape(roundCorner),
        elevation = if (type == ActionButtonType.ONLY_TEXT) ButtonDefaults.elevation(
            0.dp,
            0.dp
        ) else ButtonDefaults.elevation(),
        contentPadding = PaddingValues(),
        modifier = Modifier
            .heightIn(min = height)
            .conditional(type != ActionButtonType.SMALL) {
                fillMaxWidth()
            }
    ) {
        if (type == ActionButtonType.ONLY_TEXT) {
            Box(
                modifier = Modifier
                    .heightIn(min = height)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                if (progress) {
                    CircularProgressIndicator(
                        color = AppTheme.colors.onSurface,
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .align(Alignment.Center)
                    )
                }

                val textColor = if (progress) {
                    Color.Transparent
                } else {
                    AppTheme.colors.textButton
                }

                Text(
                    text = text,
                    style = AppTheme.typography.button,
                    color = textColor,
                    modifier = Modifier.align(Alignment.Center)
                )
                if (!progress) {
                    content()
                }
            }
        } else {
            val backgroundColor =
                if (isEnable) AppTheme.colors.actionButtonBackground else AppTheme.colors.disableActionButtonBackground

            Box(
                modifier = Modifier
                    .conditional(type != ActionButtonType.SMALL) {
                        fillMaxWidth()
                    }
                    .background(backgroundColor)
                    .heightIn(min = height)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (progress) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                }

                val textColor = if (progress) {
                    Color.Transparent
                } else {
                    if (isEnable) {
                        AppTheme.colors.textButton
                    } else {
                        AppTheme.colors.disableTextButton
                    }
                }

                Text(
                    text = text,
                    style = AppTheme.typography.button,
                    color = textColor,
                )
                if (!progress) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun ComposePreview() {
    MovieAppTheme {
        ActionButton(
            text = "Купить за 150 ₽",
            progress = false,
            type = ActionButtonType.BIG,
            onClick = {}

        ) {

        }
    }
}