package ru.shurikus.app_ui.ui.movies

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import ru.shurikus.coreNetwork.repo.MoviesRepository
import ru.shurikus.corePreferences.repositories.PrefsRepository
import ru.shurikus.corePreferences.repositories.ThemeSettingsRepository
import ru.shurikus.design_system.arch.BaseViewModel
import ru.shurikus.design_system.arch.State

@Immutable
internal data class MoviesUiState(
    val logout: Boolean = false,
    val isRefreshing: Boolean = false
) : State

internal class MoviesViewModel constructor(
    private val repo: MoviesRepository,
    private val prefsRepository: PrefsRepository,
    private val themeSettingsRepository: ThemeSettingsRepository,
) : BaseViewModel<MoviesUiState>(initialState = MoviesUiState()) {

    val movies = Pager(PagingConfig(pageSize = 20)) {
        repo.createMoviePagingSource()
    }.flow.cachedIn(viewModelScope)

    fun onLogout() {
        prefsRepository.clearAll()
        setState { it.copy(logout = true) }
    }

    fun onSwitchTheme() {
        themeSettingsRepository.isDarkTheme = !themeSettingsRepository.isDarkTheme
    }

    fun onRefresh(isStart: Boolean) {
        setState { it.copy(isRefreshing = isStart) }
    }
}