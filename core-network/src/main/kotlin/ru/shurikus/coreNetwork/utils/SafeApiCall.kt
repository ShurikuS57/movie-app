package ru.shurikus.coreNetwork.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.shurikus.core.models.ErrorEntity
import ru.shurikus.core.models.ErrorType
import ru.shurikus.coreNetwork.mappers.Mapper
import ru.shurikus.coreNetwork.models.RepoResult

suspend fun <T : Any> safeApiCall(
    call: suspend () -> Response<T>,
): RepoResult<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = call.invoke()
            return@withContext when (response.code()) {
                200 -> {
                    RepoResult.Success(response.body()!!)
                }
                401 -> {
                    RepoResult.Error(ErrorEntity(
                        errorType = ErrorType.Validation,
                        errorId = 401,
                        message = "Your login or password is invalid, please try again"
                    ))
                }
                else -> RepoResult.Error(error = ErrorEntity(
                    errorType = ErrorType.UNKNOWN,
                    message = ""
                ))
            }
        } catch (error: Throwable) {
            return@withContext ThrowableHandler.network(error)
        }
    }
}

suspend fun <T : Any, R : Any> safeApiCall(
    mapper: Mapper<T, R>,
    call: suspend () -> Response<T>,
): RepoResult<R> {
    return when (val result = safeApiCall(call)) {
        is RepoResult.Success -> {
            RepoResult.Success(mapper.toDomain(result.data))
        }
        is RepoResult.Error -> {
            RepoResult.Error(result.error)
        }
    }
}