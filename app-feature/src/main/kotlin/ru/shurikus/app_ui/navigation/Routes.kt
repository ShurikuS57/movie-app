package ru.shurikus.app_ui.navigation

internal sealed class LoginRoute(var route: String) {
    object Login : LoginRoute("login")
}

internal sealed class MainRoute(var route: String) {
    object Movies : MainRoute("movies")
    object MovieDetails : MainRoute("movies_details/{movieId}") {
        fun route(movieId: Int) = "movies_details/$movieId"
    }
}
