package ru.shurikus.app_ui.ui.movies

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.flowOf
import ru.shurikus.app_feature.R
import ru.shurikus.app_ui.extentions.rememberLazyListState
import ru.shurikus.app_ui.navigation.MainRoute
import ru.shurikus.app_ui.ui.main.MainActivity
import ru.shurikus.app_ui.ui.splash.SplashScreenActivity
import ru.shurikus.coreNetwork.models.entities.MovieEntity
import ru.shurikus.design_system.ui.components.*
import ru.shurikus.design_system.ui.dialog.YesNoAlertDialog

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MoviesScreen(
    navController: NavController,
    viewModel: MoviesViewModel,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    if (state.logout) {
        LogoutAction()
    }

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = { Text(stringResource(id = R.string.trending_movie)) }, actions = {
            IconButton(onClick = {
                viewModel.onSwitchTheme()
            }) {
                Icon(Icons.Filled.DarkMode, null, tint = AppTheme.colors.icon)
            }
            IconButton(onClick = {
                setShowDialog(true)
            }) {
                Icon(Icons.Filled.Logout, null, tint = AppTheme.colors.icon)
            }
        })
    }) {
        val moviesList: LazyPagingItems<MovieEntity> =
            viewModel.movies.collectAsLazyPagingItems()
        BuildMovieList(moviesList, state.isRefreshing,
            onRefresh = { isRefresh ->
                if (isRefresh) {
                    moviesList.refresh()
                }
                viewModel.onRefresh(isRefresh)
            },
            onClick = { movie ->
                navController.navigate(MainRoute.MovieDetails.route(movie.id))
            })
        LogoutDialog(showDialog, setShowDialog, viewModel)
    }
}

@Composable
private fun LogoutDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    viewModel: MoviesViewModel,
) {
    YesNoAlertDialog(showDialog,
        setShowDialog,
        title = stringResource(id = R.string.logout),
        message = stringResource(id = R.string.logout_description),
        positiveButtonText = stringResource(id = R.string.ok),
        negativeButtonText = stringResource(id = R.string.cancel),
        positiveButtonClick = {
            viewModel.onLogout()
        })
}

@Composable
private fun LogoutAction() {
    val context = LocalContext.current
    context.startActivity(Intent(context, SplashScreenActivity::class.java))
    if (context is MainActivity) {
        context.finish()
    }
}

@Composable
internal fun BuildMovieList(
    moviesList: LazyPagingItems<MovieEntity>,
    isRefreshing: Boolean,
    onClick: (MovieEntity) -> Unit,
    onRefresh: (Boolean) -> Unit,
) {
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing), onRefresh = {
        onRefresh.invoke(true)
    }) {
        LazyColumn(
            state = moviesList.rememberLazyListState()
        ) {
            items(moviesList) { movie ->
                movie?.let {
                    MovieCard(movie = movie, onClick)
                }
            }
            moviesList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = moviesList.loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(message = e.error.localizedMessage ?: "Fail loading",
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { retry() })
                        }
                        onRefresh.invoke(false)
                    }
                    loadState.append is LoadState.Error -> {
                        val e = moviesList.loadState.append as LoadState.Error
                        item {
                            ErrorItem(message = e.error.localizedMessage ?: "Fail loading",
                                onClickRetry = { retry() })
                        }
                    }
                    loadState.refresh is LoadState.NotLoading -> {
                        onRefresh.invoke(false)
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieCard(
    movie: MovieEntity,
    onClick: (MovieEntity) -> Unit,
) {
    Box(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 6.dp)
        .clickable {
            onClick.invoke(movie)
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = rememberAsyncImagePainter(model = movie.posterUrl,
                placeholder = ColorPainter(Color.Gray),
                error = ColorPainter(Color.Gray)),
                contentDescription = null,
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp, 150.dp)
                    .clip(RoundedCornerShape(12.dp)))
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = movie.title,
                    style = AppTheme.typography.h5,
                    color = AppTheme.colors.textH5,
                    modifier = Modifier.padding(vertical = 8.dp))
                Text(text = movie.overview,
                    maxLines = 2,
                    modifier = Modifier.padding(vertical = 8.dp))
                Box(Modifier.height(8.dp))
                BuildVoteStarts(currentVote = movie.voteAverage)
            }
        }
    }
}

@Composable
private fun BuildVoteStarts(currentVote: Int, maxVote: Int = 5) {
    Row {
        for (i in 1..maxVote) {
            val color = if (i <= currentVote) {
                AppTheme.colors.starSelected
            } else {
                AppTheme.colors.starUnelected
            }
            Icon(Icons.Filled.Star, null, tint = color, modifier = Modifier.size(14.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun MoviePreview() {
    val movies = flowOf(PagingData.from(listOf(
        MovieEntity(1,
            "Title",
            "Description",
            "https://en.wikipedia.org/wiki/File:This_Gun_for_Hire_(1942)_poster.jpg",
            3)
    ))).collectAsLazyPagingItems()

    MovieAppTheme {
        BuildMovieList(moviesList = movies, isRefreshing = false, onClick = {}, onRefresh = {})
    }
}

@Preview(showBackground = true)
@Composable
internal fun MovieItemPreview() {
    MovieAppTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            MovieCard(MovieEntity(1,
                "Title",
                "Description",
                "https://en.wikipedia.org/wiki/File:This_Gun_for_Hire_(1942)_poster.jpg",
                3)) {}
        }
    }
}