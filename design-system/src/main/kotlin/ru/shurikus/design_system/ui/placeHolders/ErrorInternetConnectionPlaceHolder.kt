package ru.shurikus.design_system.ui.placeHolders

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shurikus.design_system.R
import ru.shurikus.design_system.ui.components.AppTheme
import ru.shurikus.design_system.ui.components.MovieAppTheme

@Composable
fun ErrorInternetConnectionPlaceHolder() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 32.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_internet),
            contentDescription = "",
        )
        Text(
            text = "Oops, No Internet Connection",
            style = AppTheme.typography.h4,
            color = AppTheme.colors.textH4,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        )
        Text(
            text = "Make sure wifi or cellular data is turned on and then try again.",
            color = AppTheme.colors.textH5,
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun InternetConnectionPlaceHolderPreview() {
    MovieAppTheme {
        Surface {
            ErrorInternetConnectionPlaceHolder()
        }
    }
}

@Preview
@Composable
fun InternetConnectionPlaceHolderDarkPreview() {
    MovieAppTheme(darkTheme = true) {
        Surface {
            ErrorInternetConnectionPlaceHolder()
        }
    }
}