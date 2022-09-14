package ru.shurikus.coreNetwork.repo

import androidx.paging.PagingSource
import ru.shurikus.core.models.ErrorEntity
import ru.shurikus.core.models.ErrorType
import ru.shurikus.coreNetwork.api.ApiService
import ru.shurikus.coreNetwork.mappers.MovieDetailsMapper
import ru.shurikus.coreNetwork.mappers.MoviesMapper
import ru.shurikus.coreNetwork.models.RepoResult
import ru.shurikus.coreNetwork.models.entities.MovieDetailsEntity
import ru.shurikus.coreNetwork.models.entities.MovieEntity

interface MoviesRepository {

    fun createMoviePagingSource(): PagingSource<Int, MovieEntity>

    suspend fun loadMovieDetails(movieId: Int): RepoResult<MovieDetailsEntity>
}

internal class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val moviesMapper: MoviesMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
) : MoviesRepository {

    override fun createMoviePagingSource(): PagingSource<Int, MovieEntity> {
        return MoviePagingSource(apiService, moviesMapper)
    }

    override suspend fun loadMovieDetails(movieId: Int): RepoResult<MovieDetailsEntity> {
        return try {
            val response = apiService.requestMovieDetails(movieId)
            RepoResult.Success(movieDetailsMapper.toDomain(response))
        } catch (throwable: Throwable) {
            RepoResult.Error(
                ErrorEntity(
                    errorType = ErrorType.Response,
                    message = throwable.message.toString()
                )
            )
        }
    }
}