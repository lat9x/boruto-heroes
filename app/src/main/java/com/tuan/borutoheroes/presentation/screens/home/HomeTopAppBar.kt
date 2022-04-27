package com.tuan.borutoheroes.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.ui.theme.BorutoHeroesTheme
import com.tuan.borutoheroes.ui.theme.topAppBarBackgroundColor
import com.tuan.borutoheroes.ui.theme.topAppBarContentColor

@Composable
fun HomeTopAppBar(
    onSearchIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.explore),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchIconClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_heroes),
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            }
        }
    )
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun HomeTopAppBarPreview() {
    BorutoHeroesTheme {
        HomeTopAppBar(
            onSearchIconClick = {}
        )
    }
}