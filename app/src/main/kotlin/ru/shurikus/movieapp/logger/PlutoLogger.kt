package ru.shurikus.movieapp.logger

import com.pluto.plugins.logger.PlutoTimberTree
import ru.shurikus.core.logger.AppLogger

internal class PlutoLogger : AppLogger {

    override fun d(tag: String, message: String) {
        PlutoTimberTree().d("$tag : $message")
    }

    override fun e(t: Throwable?, message: String?, vararg args: Any?) {
        PlutoTimberTree().e(t, message, args)
    }
}
