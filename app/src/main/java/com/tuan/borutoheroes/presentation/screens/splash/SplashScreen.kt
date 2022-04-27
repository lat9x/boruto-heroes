package com.tuan.borutoheroes.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.navigation.Screen
import com.tuan.borutoheroes.ui.theme.*
import com.tuan.borutoheroes.util.Constants.FULL_ROTATION
import com.tuan.borutoheroes.util.Constants.LOGO_ANIMATION_DELAY_TIME
import com.tuan.borutoheroes.util.Constants.LOGO_ANIMATION_DURATION_TIME
import com.tuan.borutoheroes.util.Constants.NO_ROTATION

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val isOnBoardingCompleted: Boolean by viewModel.onBoardingState.collectAsState()

    val rotateDegrees = remember { Animatable(initialValue = NO_ROTATION) }

    LaunchedEffect(key1 = true) {
        // this block of code will be executed the first time Splash Screen is composed
        // rotate the app logo by 360 degrees
        rotateDegrees.animateTo(
            targetValue = FULL_ROTATION,
            animationSpec = tween(
                durationMillis = LOGO_ANIMATION_DURATION_TIME,
                delayMillis = LOGO_ANIMATION_DELAY_TIME
            )
        )
        navController.popBackStack()
        if (isOnBoardingCompleted) {
            navController.navigate(route = Screen.Home.route)
        } else {
            navController.navigate(route = Screen.Welcome.route)
        }
    }

    ScreenContent(rotateDegrees = rotateDegrees.value)
}

@Composable
private fun ScreenContent(rotateDegrees: Float) {
    Box(
        modifier = Modifier
            .background(
                brush = if (isSystemInDarkTheme())
                    Brush.verticalGradient(
                        colors = listOf(
                            DarkSplashStartGradient,
                            DarkSplashEndGradient
                        )
                    )
                else
                    Brush.verticalGradient(
                        colors = listOf(
                            LightSplashStartGradient,
                            LightSplashEndGradient
                        )
                    )
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.app_logo),
            modifier = Modifier.rotate(degrees = rotateDegrees)
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
private fun SplashScreenPreview() {
    BorutoHeroesTheme {
        ScreenContent(rotateDegrees = NO_ROTATION)
    }
}