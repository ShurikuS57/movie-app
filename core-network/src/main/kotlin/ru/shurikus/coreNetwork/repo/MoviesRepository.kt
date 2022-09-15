package ru.shurikus.coreNetwork.repo

import androidx.paging.PagingSource
import ru.shurikus.coreNetwork.api.ApiService
import ru.shurikus.coreNetwork.mappers.MovieDetailsMapper
import ru.shurikus.coreNetwork.mappers.MoviesMapper
import ru.shurikus.coreNetwork.models.RepoResult
import ru.shurikus.coreNetwork.models.entities.MovieDetailsEntity
import ru.shurikus.coreNetwork.models.entities.MovieEntity
import ru.shurikus.coreNetwork.utils.safeApiCall

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
        return safeApiCall(movieDetailsMapper) {
            apiService.requestMovieDetails(movieId)
        }
    }
}