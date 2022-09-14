package ru.shurikus.coreNetwork.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.shurikus.coreNetwork.api.ApiService
import ru.shurikus.coreNetwork.mappers.MoviesMapper
import ru.shurikus.coreNetwork.models.entities.MovieEntity

internal class MoviePagingSource(
    private val apiService: ApiService,
    private val mapper: MoviesMapper,
) : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val nextPage = params.key ?: 1
            val moviesResponse = apiService.requestTrendingMoviesPage(nextPage)
            LoadResult.Page(
                data = mapper.toDomain(moviesResponse),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = moviesResponse.page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}