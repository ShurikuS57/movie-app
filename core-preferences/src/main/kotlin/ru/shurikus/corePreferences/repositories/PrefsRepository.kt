package ru.shurikus.corePreferences.repositories

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import ru.shurikus.corePreferences.PrefKeys

interface PrefsRepository {
    fun saveStr(key: PrefKeys, value: String)
    fun loadStr(key: PrefKeys, defValue: String = ""): String

    fun saveBool(key: PrefKeys, value: Boolean)
    fun loadBool(key: PrefKeys, defValue: Boolean = false): Boolean

    fun saveInt(key: PrefKeys, value: Int)
    fun loadInt(key: PrefKeys, defValue: Int): Int

    fun saveLong(key: PrefKeys, value: Long)
    fun loadLong(key: PrefKeys, defValue: Long): Long

    fun clearAll()

    fun clearValue(key: PrefKeys)
}

internal class PrefsRepositoryImpl constructor(context: Context) :
    PrefsRepository {
    private val sp: SharedPreferences

    init {
        val masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        sp = EncryptedSharedPreferences.create(
            context,
            "prefs",
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun saveStr(key: PrefKeys, value: String) {
        sp.edit().putString(key.value, value).apply()
    }

    override fun loadStr(key: PrefKeys, defValue: String): String {
        return sp.getString(key.value, defValue) ?: defValue
    }

    override fun saveBool(key: PrefKeys, value: Boolean) {
        sp.edit().putBoolean(key.value, value).apply()
    }

    override fun loadBool(key: PrefKeys, defValue: Boolean): Boolean {
        return sp.getBoolean(key.value, defValue)
    }

    override fun saveInt(key: PrefKeys, value: Int) {
        sp.edit().putInt(key.value, value).apply()
    }

    override fun loadInt(key: PrefKeys, defValue: Int): Int {
        return sp.getInt(key.value, defValue)
    }

    override fun saveLong(key: PrefKeys, value: Long) {
        sp.edit().putLong(key.value, value).apply()
    }

    override fun loadLong(key: PrefKeys, defValue: Long): Long {
        return sp.getLong(key.value, defValue)
    }

    override fun clearAll() {
        sp.edit().clear().apply()
    }

    override fun clearValue(key: PrefKeys) {
        sp.edit().remove(key.value).apply()
    }
}
