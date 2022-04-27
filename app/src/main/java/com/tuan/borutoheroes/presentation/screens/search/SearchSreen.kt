package com.tuan.borutoheroes.presentation.screens.search

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.presentation.common.HeroesListContent
import com.tuan.borutoheroes.ui.theme.statusBarColor

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val searchedHeroes: LazyPagingItems<Hero> = viewModel.searchResult.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        topBar = {
            SearchTopBar(
                searchQuery = viewModel.searchQuery.value,
                onSearchQueryChange = { newQuery ->
                    viewModel.updateSearchQuery(newQuery = newQuery)
                },
                searchHeroes = { searchQuery ->
                    viewModel.searchHeroes(searchQuery = searchQuery)
                },
                closeSearchScreen = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        HeroesListContent(
            heroes = searchedHeroes,
            navController = navController
        )
    }
}