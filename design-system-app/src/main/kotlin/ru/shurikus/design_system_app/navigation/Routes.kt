package ru.shurikus.design_system_app.navigation


sealed class DesignSystemRoute(var route: String) {
    object MainMenu: DesignSystemRoute("main_menu")
    object ActionButtons : DesignSystemRoute("action_buttons")
    object InputText : DesignSystemRoute("input_text")
}
