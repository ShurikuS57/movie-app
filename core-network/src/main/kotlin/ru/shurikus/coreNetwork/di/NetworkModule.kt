package ru.shurikus.coreNetwork.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.shurikus.core.Const
import ru.shurikus.coreNetwork.mappers.MovieDetailsMapper
import ru.shurikus.coreNetwork.mappers.MoviesMapper
import ru.shurikus.coreNetwork.repo.AuthRepository
import ru.shurikus.coreNetwork.repo.AuthRepositoryImpl
import ru.shurikus.coreNetwork.repo.MoviesRepository
import ru.shurikus.coreNetwork.repo.MoviesRepositoryImpl

val coreNetworkModule = module {
    // Network
    single { provideOkHttpClient(getProperty("API_KEY")) }
    single { provideRetrofit(get(), Const.BASE_URL) }
    single { provideApiService(get()) }

    // Repo
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::MoviesRepositoryImpl) { bind<MoviesRepository>() }

    // Mappers
    singleOf(::MovieDetailsMapper)
    singleOf(::MoviesMapper)
}