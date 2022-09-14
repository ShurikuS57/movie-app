package ru.shurikus.app_ui.ui.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.shurikus.core.models.ErrorEntity
import ru.shurikus.coreNetwork.models.RepoResult
import ru.shurikus.coreNetwork.models.request.LoginRequest
import ru.shurikus.coreNetwork.repo.AuthRepository
import ru.shurikus.design_system.arch.BaseViewModel
import ru.shurikus.design_system.arch.State

@Immutable
internal data class LoginUiState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessages: ErrorEntity? = null,
    val isLoggedIn: Boolean = false,
    val isButtonEnable: Boolean = false,
) : State

internal class LoginViewModel constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel<LoginUiState>(LoginUiState()) {

    fun login() {
        setState { it.copy(isLoading = true) }
        val login = state.value.login
        val password = state.value.password
        viewModelScope.launch {
            when (val result =
                authRepository.login(LoginRequest(username = login, password = password))) {
                is RepoResult.Error -> {
                    setState { it.copy(isLoading = false, errorMessages = result.error) }
                }
                is RepoResult.Success -> {
                    setState { it.copy(isLoading = false, isLoggedIn = true) }
                }
            }
        }
    }

    fun onLoginChange(loginString: String) {
        setState { it.copy(login = loginString.trim(), errorMessages = null) }
        invalidateButton()
    }

    fun onPasswordChange(password: String) {
        setState { it.copy(password = password.trim(), errorMessages = null) }
        invalidateButton()
    }

    private fun invalidateButton() {
        val login = state.value.login
        val password = state.value.password
        val isEnableButton = login.length > 4 && password.length > 4
        setState { it.copy(isButtonEnable = isEnableButton) }
    }
}
