package ru.shurikus.coreNetwork.mappers

import ru.shurikus.core.Const
import ru.shurikus.coreNetwork.models.entities.MovieDetailsEntity
import ru.shurikus.coreNetwork.models.response.MovieDetailsResponse

internal class MovieDetailsMapper {
    fun toDomain(response: MovieDetailsResponse): MovieDetailsEntity {
        val title = response.title
        val description = response.overview
        val backgroundImageUrl = Const.IMAGE_URL + response.backdropPath
        val duration = prepareDuration(response.runtime)

        return MovieDetailsEntity(
            title = title,
            description = description,
            backgroundImageUrl = backgroundImageUrl,
            duration = duration,
            genres = response.genres.map { it.name }
        )
    }

    private fun prepareDuration(runtime: Int): String {
        var result = ""
        val hors = runtime / 60
        val minute = runtime % 60
        if (hors > 0) {
            result += hors.toString() + "h "
        }
        result += minute.toString() + "m"
        return result
    }
}
