package ru.shurikus.corePreferences.repositories

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.shurikus.corePreferences.PrefKeys
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface ThemeSettingsRepository {
    val themeState: StateFlow<Boolean>
    var isDarkTheme: Boolean
}

internal class ThemeSettingsRepositoryImpl constructor(
    private val prefsRepo: PrefsRepository,
) : ThemeSettingsRepository {

    override var isDarkTheme: Boolean by AppThemePreferenceDelegate(true)
    override val themeState: MutableStateFlow<Boolean> = MutableStateFlow(isDarkTheme)

    inner class AppThemePreferenceDelegate(
        private val default: Boolean,
    ) : ReadWriteProperty<Any?, Boolean> {

        override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
            return prefsRepo.loadBool(PrefKeys.IS_DARK_THEME, default)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
            themeState.value = value
            prefsRepo.saveBool(PrefKeys.IS_DARK_THEME, value)
        }
    }
}
