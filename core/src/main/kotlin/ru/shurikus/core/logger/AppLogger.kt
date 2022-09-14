package ru.shurikus.core.logger

import org.jetbrains.annotations.NonNls

interface AppLogger {
    fun d(tag: String, message: String)
    fun e(t: Throwable?, @NonNls message: String?, vararg args: Any?)
}