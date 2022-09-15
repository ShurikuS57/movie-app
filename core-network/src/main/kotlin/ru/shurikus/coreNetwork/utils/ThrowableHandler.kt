package ru.shurikus.coreNetwork.utils

import retrofit2.HttpException
import ru.shurikus.core.models.ErrorEntity
import ru.shurikus.core.models.ErrorType
import ru.shurikus.coreNetwork.models.RepoResult
import java.io.IOException

internal object ThrowableHandler {

    fun network(throwable: Throwable): RepoResult.Error {
        return when (throwable) {
            is IOException ->
                RepoResult.Error(
                    ErrorEntity(
                        errorType = ErrorType.Connection,
                        message = "Network connection failed.\n${throwable.message}"
                    )
                )
            is HttpException -> {
                val code = throwable.code()
                RepoResult.Error(
                    ErrorEntity(
                        errorType = ErrorType.Connection,
                        message = "$code ${throwable.message}"
                    )
                )
            }
            else -> {
                RepoResult.Error(ErrorEntity(
                    errorType = ErrorType.Response,
                    message = throwable.message.toString()
                ))
            }
        }
    }
}