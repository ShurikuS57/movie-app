package ru.shurikus.coreNetwork.models.entities

data class MovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val voteAverage: Int
)
