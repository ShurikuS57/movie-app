package ru.shurikus.coreNetwork.models

import ru.shurikus.core.models.ErrorEntity

sealed class RepoResult<out R> {
    data class Success<out T>(val data: T) : RepoResult<T>()
    data class Error(val error: ErrorEntity) : RepoResult<Nothing>()
}

fun <T> RepoResult<T>.successOr(fallback: T): T {
    return (this as? RepoResult.Success<T>)?.data ?: fallback
}
