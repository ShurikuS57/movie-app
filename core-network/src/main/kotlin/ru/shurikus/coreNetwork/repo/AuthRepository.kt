package ru.shurikus.coreNetwork.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.shurikus.core.models.ErrorEntity
import ru.shurikus.core.models.ErrorType
import ru.shurikus.coreNetwork.api.ApiService
import ru.shurikus.coreNetwork.models.RepoResult
import ru.shurikus.coreNetwork.models.request.LoginRequest
import ru.shurikus.coreNetwork.utils.ThrowableHandler
import ru.shurikus.corePreferences.PrefKeys
import ru.shurikus.corePreferences.repositories.PrefsRepository

interface AuthRepository {
    suspend fun login(request: LoginRequest): RepoResult<Boolean>
}

internal class AuthRepositoryImpl constructor(
    private val apiService: ApiService,
    private val prefsRepo: PrefsRepository,
) : AuthRepository {

    override suspend fun login(request: LoginRequest): RepoResult<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val tokenResponse = apiService.requestNewToken()
                if (tokenResponse.success && tokenResponse.requestToken.isNotBlank()) {
                    request.requestToken = tokenResponse.requestToken
                    prefsRepo.saveStr(PrefKeys.REQUEST_TOKEN, request.requestToken)
                } else {
                    RepoResult.Error(ErrorEntity(
                        errorType = ErrorType.Response,
                        message = "Error request new token"
                    ))
                }
                val sessionResponse = apiService.createUserSession(request)
                if (sessionResponse.success && sessionResponse.requestToken.isNotBlank()) {
                    sessionResponse.requestToken
                    prefsRepo.saveStr(PrefKeys.SESSION_TOKEN, sessionResponse.requestToken)
                } else {
                    RepoResult.Error(ErrorEntity(
                        errorType = ErrorType.Response,
                        message = "Error request session token"
                    ))
                }
                RepoResult.Success(true)
            } catch (throwable: Throwable) {
                if (throwable is HttpException) {
                    if (throwable.code() == 401) {
                        RepoResult.Error(ErrorEntity(
                            errorType = ErrorType.Validation,
                            errorId = 401,
                            message = "Your login or password is invalid, please try again"
                        ))
                    } else {
                        RepoResult.Error(ErrorEntity(
                            errorType = ErrorType.Response,
                            message = "Login error"
                        ))
                    }
                } else {
                    ThrowableHandler.network(throwable)
                }
            }
        }
    }
}
