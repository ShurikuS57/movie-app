package ru.shurikus.design_system_app.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.shurikus.design_system_app.screens.menu.MenuViewModel

val catalogModules = module {
    viewModelOf(::MenuViewModel)
}