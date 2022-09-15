package ru.shurikus.coreNetwork.mappers

interface Mapper<T: Any, R: Any> {
    fun toDomain(response: T): R
}