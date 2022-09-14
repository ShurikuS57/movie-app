package ru.shurikus.app_ui.ui.splash

import androidx.lifecycle.ViewModel
import ru.shurikus.corePreferences.PrefKeys
import ru.shurikus.corePreferences.repositories.PrefsRepository

internal class SplashViewModel constructor(
    private val prefs: PrefsRepository
) : ViewModel() {

    fun isUserLogin(): Boolean {
        val sessionToken = prefs.loadStr(PrefKeys.SESSION_TOKEN)
        return sessionToken.isNotBlank()
    }
}
