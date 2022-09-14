package ru.shurikus.movieapp.logger

import ru.shurikus.core.logger.AppLogger
import ru.shurikus.movieapp.BuildConfig

class AppLoggerImpl : AppLogger {
    private val logList: MutableList<AppLogger> = mutableListOf()

    init {
        if (BuildConfig.DEBUG) {
            logList.add(PlutoLogger())
        }
    }

    override fun d(tag: String, message: String) {
        logList.forEach { it.d(tag, message) }
    }

    override fun e(t: Throwable?, message: String?, vararg args: Any?) {
        logList.forEach { it.e(t, message, args) }
    }
}
