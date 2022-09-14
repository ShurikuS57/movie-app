package ru.shurikus.movieapp.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.shurikus.core.logger.AppLogger
import ru.shurikus.movieapp.logger.AppLoggerImpl

val appModules = module {
    singleOf(::AppLoggerImpl) { bind<AppLogger>() }
}
