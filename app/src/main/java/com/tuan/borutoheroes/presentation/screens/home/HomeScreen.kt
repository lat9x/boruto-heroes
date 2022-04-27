package com.tuan.borutoheroes.presentation.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.navigation.Screen
import com.tuan.borutoheroes.presentation.common.HeroesListContent
import com.tuan.borutoheroes.ui.theme.statusBarColor

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val allHeroes: LazyPagingItems<Hero> = viewModel.allHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onSearchIconClick = {
                    navController.navigate(Screen.Search.route)
                }
            )
        }
    ) {
        HeroesListContent(heroes = allHeroes, navController = navController)
    }
}