package ru.shurikus.app_ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.koin.androidx.compose.inject
import ru.shurikus.app_ui.ui.movieDetails.MovieDetailsScreen
import ru.shurikus.app_ui.ui.movies.MoviesScreen
import ru.shurikus.app_ui.ui.movies.MoviesViewModel

@Composable
internal fun MainNavHost(navController: NavHostController) {
    // Fix save scroll position. After recreation, LazyPagingItems return cached items. But only pageSize item scroll write.
    // Single MoviesViewModel in MainNavHost fix it.
    val viewModel: MoviesViewModel by inject()

    NavHost(
        navController = navController,
        startDestination = MainRoute.Movies.route
    ) {
        composable(MainRoute.Movies.route) {
            MoviesScreen(navController = navController, viewModel)
        }
        composable(
            MainRoute.MovieDetails.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            MovieDetailsScreen(
                movieId = backStackEntry.arguments?.getInt("movieId") ?: 0,
                navController = navController
            )
        }
    }
}
