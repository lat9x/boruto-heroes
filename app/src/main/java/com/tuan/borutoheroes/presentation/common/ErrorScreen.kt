package com.tuan.borutoheroes.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.ui.theme.BorutoHeroesTheme
import com.tuan.borutoheroes.ui.theme.ERROR_IMAGE_SIZE
import com.tuan.borutoheroes.ui.theme.SMALL_PADDING
import com.tuan.borutoheroes.ui.theme.errorScreenMessageColor
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun ErrorScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null
) {
    @StringRes var message: Int by remember {
        mutableStateOf(value = R.string.find_your_favorite_heroes)
    }
    @DrawableRes var icon: Int by remember {
        mutableStateOf(value = R.drawable.ic_search_document)
    }

    if (error != null) {
        message = parseErrorMessage(error = error)
        icon = R.drawable.ic_network_error
    }

    var startAnimation: Boolean by remember { mutableStateOf(value = false) }
    val alphaAnim: Float by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    SwipeToRefresh(
        error = error,
        heroes = heroes,
        alphaAnim = alphaAnim,
        icon = icon,
        message = message
    )
}

@Composable
private fun SwipeToRefresh(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null,
    alphaAnim: Float,
    @DrawableRes icon: Int,
    @StringRes message: Int
) {
    var isRefreshing: Boolean by remember { mutableStateOf(value = false) }

    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true

            // refresh OR invalidate the data
            heroes?.refresh()

            isRefreshing = false
        }
    ) {
        ScreenContent(
            alphaAnim = alphaAnim,
            icon = icon,
            message = message
        )
    }
}

@Composable
private fun ScreenContent(
    alphaAnim: Float,
    @DrawableRes icon: Int,
    @StringRes message: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(size = ERROR_IMAGE_SIZE)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.error_image),
            tint = MaterialTheme.colors.errorScreenMessageColor
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alpha = alphaAnim),
            text = stringResource(id = message),
            color = MaterialTheme.colors.errorScreenMessageColor,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}

private fun parseErrorMessage(error: LoadState.Error): Int {
    return when (error.error) {
        is SocketTimeoutException -> R.string.server_down
        is ConnectException -> R.string.internet_down
        else -> R.string.unknown_error
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun ErrorScreenPreview() {
    BorutoHeroesTheme {
        ScreenContent(
            alphaAnim = 0f,
            icon = R.drawable.ic_network_error,
            message = R.string.internet_down
        )
    }
}