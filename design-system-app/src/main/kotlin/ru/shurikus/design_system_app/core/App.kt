package ru.shurikus.design_system_app.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.shurikus.corePreferences.di.corePreferencesModule
import ru.shurikus.design_system_app.di.catalogModules

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                corePreferencesModule,
                catalogModules,
            )
        }
    }
}