package ru.shurikus.core.models

data class ErrorEntity(
    val errorType: ErrorType,
    val errorId: Int = 1,
    val messageTitle: String = "",
    val message: String = "",
    val fieldErrors: Map<String, Any>? = mapOf(),
)

sealed class ErrorType {
    object Validation : ErrorType()
    object Response : ErrorType()
    object Connection : ErrorType()
    object UNKNOWN : ErrorType()
}