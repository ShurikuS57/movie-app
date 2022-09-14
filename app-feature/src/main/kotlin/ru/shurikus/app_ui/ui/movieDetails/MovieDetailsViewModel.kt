package ru.shurikus.app_ui.ui.movieDetails

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.shurikus.core.models.ErrorEntity
import ru.shurikus.coreNetwork.models.RepoResult
import ru.shurikus.coreNetwork.models.entities.MovieDetailsEntity
import ru.shurikus.coreNetwork.repo.MoviesRepository
import ru.shurikus.design_system.arch.BaseViewModel
import ru.shurikus.design_system.arch.State

@Immutable
internal data class MoviesDetailsUiState(
    val movieDetails: MovieDetailsEntity? = null,
    val logout: Boolean = false,
    val isLoading: Boolean = true,
    val errorMessages: ErrorEntity? = null,
    val showPlaceholder: Boolean = true,
) : State

internal class MovieDetailsViewModel constructor(
    private val repo: MoviesRepository,
) : BaseViewModel<MoviesDetailsUiState>(
    initialState = MoviesDetailsUiState()
) {

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            when (val result = repo.loadMovieDetails(movieId = movieId)) {
                is RepoResult.Error -> {
                    setState {
                        it.copy(
                            errorMessages = result.error,
                            isLoading = false,
                        )
                    }
                }
                is RepoResult.Success -> {
                    setState { it.copy(movieDetails = result.data, isLoading = false) }
                }
            }
        }
    }
}
