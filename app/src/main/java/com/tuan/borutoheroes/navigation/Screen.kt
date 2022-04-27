package com.tuan.borutoheroes.navigation

import com.tuan.borutoheroes.util.Constants.DETAILS_SCREEN_ROUTE
import com.tuan.borutoheroes.util.Constants.DETAILS_SCREEN_ROUTE_PRE
import com.tuan.borutoheroes.util.Constants.HOME_SCREEN_ROUTE
import com.tuan.borutoheroes.util.Constants.SEARCH_SCREEN_ROUTE
import com.tuan.borutoheroes.util.Constants.SPLASH_SCREEN_ROUTE
import com.tuan.borutoheroes.util.Constants.WELCOME_SCREEN_ROUTE

sealed class Screen(val route: String) {
    object Splash : Screen(route = SPLASH_SCREEN_ROUTE)
    object Welcome : Screen(route = WELCOME_SCREEN_ROUTE)
    object Home : Screen(route = HOME_SCREEN_ROUTE)
    object Search : Screen(route = SEARCH_SCREEN_ROUTE)
    object Details : Screen(route = DETAILS_SCREEN_ROUTE) {
        fun passHeroId(heroId: Int): String {
            return "$DETAILS_SCREEN_ROUTE_PRE/$heroId"
        }
    }
}
