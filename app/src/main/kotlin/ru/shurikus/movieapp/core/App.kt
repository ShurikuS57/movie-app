package ru.shurikus.movieapp.core

import android.app.Application
import com.pluto.Pluto
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.network.PlutoNetworkPlugin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.shurikus.app_ui.di.appUiModules
import ru.shurikus.coreNetwork.di.coreNetworkModule
import ru.shurikus.corePreferences.di.corePreferencesModule
import ru.shurikus.movieapp.BuildConfig
import ru.shurikus.movieapp.di.appModules

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initPluto()
    }

    private fun initPluto() {
        Pluto.Installer(this)
            .addPlugin(PlutoNetworkPlugin("network"))
            .addPlugin(PlutoExceptionsPlugin("exceptions"))
            .addPlugin(PlutoLoggerPlugin("logger"))
            .install()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            properties(mapOf(
                "API_KEY" to BuildConfig.API_KEY)
            )
            modules(
                corePreferencesModule,
                coreNetworkModule,
                appUiModules,
                appModules,
            )
        }
    }
}
