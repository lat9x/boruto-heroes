package com.tuan.borutoheroes.presentation.screens.search

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.ui.theme.BorutoHeroesTheme
import com.tuan.borutoheroes.ui.theme.TOP_BAR_HEIGHT
import com.tuan.borutoheroes.ui.theme.topAppBarBackgroundColor
import com.tuan.borutoheroes.ui.theme.topAppBarContentColor
import com.tuan.borutoheroes.util.Constants.EMPTY_SEARCH_QUERY

@Composable
fun SearchTopBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchHeroes: (String) -> Unit,
    closeSearchScreen: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = TOP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    text = stringResource(id = R.string.search_here),
                    color = Color.White
                )
            },
            textStyle = TextStyle(color = MaterialTheme.colors.topAppBarContentColor),
            singleLine = true,
            leadingIcon = {
                Icon(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon),
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (searchQuery.isNotEmpty()) {
                            onSearchQueryChange(EMPTY_SEARCH_QUERY)
                        } else {
                            closeSearchScreen()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_search_screen),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    searchHeroes(searchQuery)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topAppBarContentColor
            )
        )
    }
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
private fun SearchTopBarPreview() {
    BorutoHeroesTheme {
        SearchTopBar(
            searchQuery = "",
            onSearchQueryChange = {},
            searchHeroes = {},
            closeSearchScreen = {}
        )
    }
}