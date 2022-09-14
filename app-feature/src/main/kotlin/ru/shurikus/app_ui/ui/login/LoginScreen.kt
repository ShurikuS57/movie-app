package ru.shurikus.app_ui.ui.login

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.get
import ru.shurikus.app_feature.R
import ru.shurikus.app_ui.ui.main.MainActivity
import ru.shurikus.core.models.ErrorEntity
import ru.shurikus.design_system.ui.components.*
import ru.shurikus.design_system.ui.placeHolders.LoadingPlaceHolder
import ru.shurikus.design_system.ui.theme.Gray500

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel = get(),
) {
    val context = LocalContext.current
    Scaffold(
        backgroundColor = AppTheme.colors.uiBackground
    ) {
        val state by viewModel.state.collectAsStateWithLifecycle()
        if (state.isLoading) {
            LoadingPlaceHolder()
        } else if (state.isLoggedIn) {
            openMainScreen(context)
        } else {
            LoginContent(
                loginText = state.login,
                passwordText = state.password,
                isEnableButton = state.isButtonEnable,
                error = catchErrorMessage(state.errorMessages),
                onLoginChanged = {
                    viewModel.onLoginChange(it)
                },
                onPasswordChanged = {
                    viewModel.onPasswordChange(it)
                },
                onLoginButtonClicked = {
                    viewModel.login()
                }
            )
        }
    }
}

@Composable
private fun openMainScreen(context: Context) {
    context.startActivity(Intent(context, MainActivity::class.java))
    if (context is LoginActivity) {
        context.finish()
    }
}

fun catchErrorMessage(errorMessages: ErrorEntity?): String {
    return errorMessages?.message ?: ""
}

@Composable
private fun LoginContent(
    loginText: String = "",
    passwordText: String = "",
    error: String = "",
    isEnableButton: Boolean,
    onLoginChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.login),
            style = AppTheme.typography.h4,
            color = AppTheme.colors.textH4
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(id = R.string.message_login),
            style = AppTheme.typography.body1,
            color = Gray500
        )
        Spacer(modifier = Modifier.height(40.dp))
        TextInputField(
            loginText,
            stringResource(id = R.string.login_or_email),
            Icons.Default.AccountCircle,
            onTextChange = onLoginChanged,
            focusManager = focusManager
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextInputField(
            textValue = passwordText,
            textLabel = stringResource(id = R.string.password),
            isPasswordVisibility = true,
            leadingIcon = Icons.Default.Lock,
            onTextChange = onPasswordChanged,
            focusManager = focusManager
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(error, color = Color.Red)
        Spacer(modifier = Modifier.height(20.dp))
        LoginActionButton(
            isEnableButton = isEnableButton,
            onClick = onLoginButtonClicked
        )
    }
}

@Composable
private fun LoginActionButton(
    isEnableButton: Boolean = true,
    isProgress: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        ActionButton(
            text = "",
            isEnable = isEnableButton,
            type = ActionButtonType.SMALL,
            progress = isProgress,
            onClick = {
                onClick.invoke()
            }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
            ) {
                val textColor = if (isProgress) {
                    Color.Transparent
                } else {
                    AppTheme.colors.textButton
                }
                Text(
                    text = stringResource(id = R.string.login).uppercase(),
                    color = textColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(all = 0.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Icon(imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = textColor)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun LoginScreenViewPreview() {
    MovieAppTheme {
        LoginContent("", "", "", false, {}, {}, {})
    }
}

@Preview
@Composable
internal fun LoginScreenViewDarkPreview() {
    MovieAppTheme(darkTheme = true) {
        LoginContent("", "", "", false, {}, {}, {})
    }
}
