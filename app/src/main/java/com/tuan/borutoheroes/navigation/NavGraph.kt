package com.tuan.borutoheroes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tuan.borutoheroes.presentation.screens.details.DetailsScreen
import com.tuan.borutoheroes.presentation.screens.home.HomeScreen
import com.tuan.borutoheroes.presentation.screens.search.SearchScreen
import com.tuan.borutoheroes.presentation.screens.splash.SplashScreen
import com.tuan.borutoheroes.presentation.screens.welcome.WelcomeScreen
import com.tuan.borutoheroes.util.Constants.DETAILS_SCREEN_ARGS_HERO_ID

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(name = DETAILS_SCREEN_ARGS_HERO_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailsScreen(navController = navController)
        }
    }
}