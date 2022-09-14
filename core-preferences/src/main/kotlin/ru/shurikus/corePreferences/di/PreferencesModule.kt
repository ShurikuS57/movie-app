package ru.shurikus.corePreferences.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.shurikus.corePreferences.repositories.PrefsRepository
import ru.shurikus.corePreferences.repositories.PrefsRepositoryImpl
import ru.shurikus.corePreferences.repositories.ThemeSettingsRepository
import ru.shurikus.corePreferences.repositories.ThemeSettingsRepositoryImpl

val corePreferencesModule = module {
    singleOf(::PrefsRepositoryImpl) { bind<PrefsRepository>() }
    singleOf(::ThemeSettingsRepositoryImpl) { bind<ThemeSettingsRepository>() }
}
