package ru.shurikus.design_system.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ru.shurikus.design_system.ui.theme.OrangeFF

@Composable
fun TextInputField(
    textValue: String = "",
    textLabel: String = "",
    leadingIcon: ImageVector? = null,
    isPasswordVisibility: Boolean = false,
    focusManager: FocusManager? = null,
    onTextChange: (String) -> Unit,
) {
    val leadingIconValue: @Composable (() -> Unit)? = if (leadingIcon != null) {
        { Icon(imageVector = leadingIcon, contentDescription = null) }
    } else {
        null
    }

    TextField(
        value = textValue,
        onValueChange = onTextChange,
        label = { Text(textLabel) },
        maxLines = 1,
        leadingIcon = leadingIconValue,
        visualTransformation = if (isPasswordVisibility) PasswordVisualTransformation()
        else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager?.moveFocus(FocusDirection.Down) }
        ),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = OrangeFF,
            focusedLabelColor = OrangeFF,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            .fillMaxWidth(),
    )
}