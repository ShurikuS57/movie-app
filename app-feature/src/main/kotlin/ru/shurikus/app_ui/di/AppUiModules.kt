package ru.shurikus.app_ui.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.shurikus.app_ui.ui.login.LoginViewModel
import ru.shurikus.app_ui.ui.movieDetails.MovieDetailsViewModel
import ru.shurikus.app_ui.ui.movies.MoviesViewModel
import ru.shurikus.app_ui.ui.splash.SplashViewModel

val appUiModules = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::MovieDetailsViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::MoviesViewModel)
}
