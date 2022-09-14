package ru.shurikus.coreNetwork.models.entities

data class MovieDetailsEntity(
    val title: String,
    val description: String,
    val backgroundImageUrl: String,
    val duration: String,
    val genres: List<String>
)
