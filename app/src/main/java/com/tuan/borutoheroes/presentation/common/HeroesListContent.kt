package com.tuan.borutoheroes.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.presentation.components.HeroItem
import com.tuan.borutoheroes.presentation.components.ShimmerEffect
import com.tuan.borutoheroes.ui.theme.SMALL_PADDING
import com.tuan.borutoheroes.util.Constants.NO_HEROES_FOUND

@Composable
fun HeroesListContent(
    heroes: LazyPagingItems<Hero>,
    navController: NavHostController
) {
    val loadSuccessfully: Boolean = loadHeroesSuccessfully(heroes = heroes)

    if (loadSuccessfully) {
        // displays all heroes
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(space = SMALL_PADDING)
        ) {
            items(
                items = heroes,
                key = { hero -> hero.id }
            ) { hero ->
                hero?.let {
                    HeroItem(hero = it, navController = navController)
                }
            }
        }
    }
}

/**
 * Check the LoadState.
 * If LoadState has any error -> Handles the error, then returns false
 * If LoadState is Loading -> Display ShimmerEffect, then returns false
 * else -> returns true
 *
 * @param heroes Paging Items of type Hero
 * @return true if load successfully. Else, return false
 */
@Composable
private fun loadHeroesSuccessfully(heroes: LazyPagingItems<Hero>): Boolean {
    heroes.apply {
        // get all errors
        val error: LoadState.Error? = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            // TODO: Why not receive Server Unavailable? Because there's a bug
            // TODO: My app is request only the first page, while the learning app request first 3 page???
            error != null -> {
                ErrorScreen(error = error, heroes = heroes)
                false
            }
            heroes.itemCount == NO_HEROES_FOUND -> {
                ErrorScreen()
                false
            }
            else -> true
        }
    }
}