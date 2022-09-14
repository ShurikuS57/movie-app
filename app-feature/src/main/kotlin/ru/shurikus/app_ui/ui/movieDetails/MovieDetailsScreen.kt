package ru.shurikus.app_ui.ui.movieDetails

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.get
import ru.shurikus.app_feature.R
import ru.shurikus.coreNetwork.models.entities.MovieDetailsEntity
import ru.shurikus.design_system.ui.components.AppTheme
import ru.shurikus.design_system.ui.components.MovieAppTheme
import ru.shurikus.design_system.ui.placeHolders.LoadingContent
import ru.shurikus.design_system.ui.placeHolders.LoadingContentParams

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MovieDetailsScreen(
    movieId: Int,
    navController: NavHostController,
    viewModel: MovieDetailsViewModel = get(),
) {
    viewModel.loadMovieDetails(movieId)
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val state by viewModel.state.collectAsStateWithLifecycle()
    MovieDetailsContent(state, scaffoldState, navController)
}

@Composable
private fun MovieDetailsContent(
    state: MoviesDetailsUiState,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
) {
    Scaffold {
        LoadingContent(params = LoadingContentParams(
            loading = state.isLoading,
            error = state.errorMessages,
            showPlaceHolder = state.showPlaceholder,
        ), scaffoldState = scaffoldState) {
            state.movieDetails?.let { data ->
                MovieDetailsBody(data) {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
private fun MovieDetailsBody(value: MovieDetailsEntity, onBackClicked: () -> Unit) {
    Column {
        Header(value = value, onBackClicked)
        FilmActionButtons()
        Description(value.description)
    }
}

@Composable
private fun Header(
    value: MovieDetailsEntity,
    onBackClicked: () -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(260.dp)) {
        Image(
            painter = rememberAsyncImagePainter(value.backgroundImageUrl,
                placeholder = ColorPainter(Color.Gray),
                error = ColorPainter(Color.Gray)),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Box(modifier = Modifier
            .background(brush = Brush.verticalGradient(colors = listOf(AppTheme.colors.boxGradientEnd,
                AppTheme.colors.boxGradientStart)))
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .height(200.dp))

        Box(modifier = Modifier.background(brush = Brush.verticalGradient(colors = listOf(AppTheme.colors.boxGradientStart,
            AppTheme.colors.boxGradientEnd)))) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TopActionButton(Icons.Filled.ArrowBack, onBackClicked)
                Spacer(modifier = Modifier.weight(1f))
                TopActionButton(Icons.Filled.Favorite, onBackClicked)
            }
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(162.dp))
            Text(value.title, color = AppTheme.colors.textH5, style = AppTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Text(value.duration,
                style = AppTheme.typography.caption,
                color = AppTheme.colors.textH5)
            LazyRow(verticalAlignment = Alignment.CenterVertically) {
                items(count = value.genres.count(), itemContent = { index ->
                    val item = value.genres[index]
                    Text(item, style = AppTheme.typography.caption, color = AppTheme.colors.textH5)
                    if (index < value.genres.size - 1) {
                        Box(Modifier.width(6.dp))
                        Box(modifier = Modifier
                            .size(4.dp)
                            .padding(top = 2.dp)
                            .clip(CircleShape)
                            .background(AppTheme.colors.actionButtonBackground))
                        Box(Modifier.width(6.dp))
                    }
                })
            }
        }
    }
}

@Composable
private fun TopActionButton(image: ImageVector, onBackClicked: () -> Unit) {
    IconButton(onClick = { onBackClicked.invoke() }) {
        Box(Modifier
            .background(color = AppTheme.colors.iconButtonBackground,
                shape = RoundedCornerShape(12.dp))
            .padding(6.dp)) {
            Icon(image, null, tint = AppTheme.colors.icon)
        }
    }
}

@Composable
private fun FilmActionButtons() {
    val context = LocalContext.current
    Box(modifier = Modifier.background(brush = Brush.verticalGradient(colors = listOf(AppTheme.colors.boxGradientStart,
        AppTheme.colors.boxGradientEnd)))) {
        Column {
            Box(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier
                .height(1.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .background(color = AppTheme.colors.actionButtonBackground))
            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                TopActionButton(icon = Icons.Filled.PlayArrow,
                    text = stringResource(id = R.string.trailer)) {
                    showToast(context, "Trailer button clicked")
                }
                Spacer(modifier = Modifier
                    .width(1.dp)
                    .height(50.dp)
                    .background(AppTheme.colors.actionButtonBackground))
                TopActionButton(icon = Icons.Filled.Done, text = stringResource(id = R.string.download)) {
                    showToast(context, "Download button clicked")
                }
                Spacer(modifier = Modifier
                    .width(1.dp)
                    .height(50.dp)
                    .background(AppTheme.colors.actionButtonBackground))
                TopActionButton(icon = Icons.Filled.Share, text = stringResource(id = R.string.share)) {
                    showToast(context, "Share button clicked")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier
                .height(1.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .background(color = AppTheme.colors.actionButtonBackground))
        }
    }
}

@Composable
private fun TopActionButton(icon: ImageVector, text: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onClick.invoke() }) {
        Icon(icon, "contentDescription", tint = AppTheme.colors.actionButtonBackground)
        Text(text = text, color = AppTheme.colors.actionButtonBackground)
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
private fun Description(descriptionText: String) {
    Box(Modifier.padding(16.dp)) {
        Text(descriptionText)
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    MovieAppTheme {
        MovieDetailsContent(
            MoviesDetailsUiState(movieDetails = MovieDetailsEntity(title = "Worth",
                description = "Description text",
                backgroundImageUrl = "https://image.tmdb.org/t/p/w500/A2tQrEbK8T9gxa6kLW7Fc65EXlO.jpg",
                duration = "1h 22m",
                genres = listOf("History", "Drama")), isLoading = false),
            scaffoldState = rememberScaffoldState(), navController = rememberNavController(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsDarkPreview() {
    MovieAppTheme(darkTheme = true) {
        MovieDetailsContent(
            MoviesDetailsUiState(movieDetails = MovieDetailsEntity(title = "Worth",
                description = "Description text",
                backgroundImageUrl = "https://image.tmdb.org/t/p/w500/A2tQrEbK8T9gxa6kLW7Fc65EXlO.jpg",
                duration = "1h 22m",
                genres = listOf("History", "Drama")), isLoading = false),
            scaffoldState = rememberScaffoldState(), navController = rememberNavController(),
        )
    }
}
