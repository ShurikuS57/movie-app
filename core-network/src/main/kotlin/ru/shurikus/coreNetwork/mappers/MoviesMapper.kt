package ru.shurikus.coreNetwork.mappers

import ru.shurikus.core.Const
import ru.shurikus.coreNetwork.models.entities.MovieEntity
import ru.shurikus.coreNetwork.models.response.MoviesTrendingResponse
import kotlin.math.roundToInt

internal class MoviesMapper {
    fun toDomain(response: MoviesTrendingResponse): List<MovieEntity> {
        val list = arrayListOf<MovieEntity>()
        response.results.forEach {
            list.add(prepareMovie(it))
        }
        return list
    }

    private fun prepareMovie(movie: MoviesTrendingResponse.Result): MovieEntity {
        val movieId = movie.id
        val title = movie.title
        val overview = movie.overview
        val posterUrl = Const.IMAGE_URL + movie.posterPath
        val voteAverage = (movie.voteAverage / 2).roundToInt()

        return MovieEntity(
            id = movieId,
            title = title,
            overview = overview,
            posterUrl = posterUrl,
            voteAverage = voteAverage
        )
    }
}
