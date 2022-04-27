package com.tuan.borutoheroes.presentation.screens.details

import android.content.res.Configuration
import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.presentation.components.BottomSheetContent
import com.tuan.borutoheroes.ui.theme.BorutoHeroesTheme
import com.tuan.borutoheroes.ui.theme.EXPANDED_RADIUS_LEVEL
import com.tuan.borutoheroes.ui.theme.EXTRA_LARGE_PADDING
import com.tuan.borutoheroes.ui.theme.MIN_SHEET_HEIGHT
import com.tuan.borutoheroes.util.Constants.BLACK_COLOR
import com.tuan.borutoheroes.util.Constants.DARK_VIBRANT_COLOR
import com.tuan.borutoheroes.util.Constants.LOCAL_HOST_BASE_URL
import com.tuan.borutoheroes.util.Constants.ON_DARK_VIBRANT_COLOR
import com.tuan.borutoheroes.util.Constants.VIBRANT_COLOR
import com.tuan.borutoheroes.util.Constants.WHITE_COLOR
import com.tuan.borutoheroes.util.PaletteGenerator.convertImageUrlToBitmap
import com.tuan.borutoheroes.util.PaletteGenerator.extractColorFromBitmap
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val hero: Hero? by viewModel.hero.collectAsState()
    val colorPalette by viewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        hero?.let {
            ScreenContent(
                navController = navController,
                hero = it,
                colors = colorPalette
            )
        }
    } else {
        viewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$LOCAL_HOST_BASE_URL${hero?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        viewModel.setColorPalette(
                            colors = extractColorFromBitmap(bitmap = bitmap)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ScreenContent(
    navController: NavHostController,
    hero: Hero,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf(value = BLACK_COLOR) }
    var darkVibrant by remember { mutableStateOf(value = BLACK_COLOR) }
    var onDarkVibrant by remember { mutableStateOf(value = WHITE_COLOR) }

    LaunchedEffect(key1 = hero) {
        vibrant = colors[VIBRANT_COLOR]!!
        darkVibrant = colors[DARK_VIBRANT_COLOR]!!
        onDarkVibrant = colors[ON_DARK_VIBRANT_COLOR]!!
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrant))
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = Expanded)
    )

    val currentSheetFraction: Float = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL,
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = radiusAnim, topEnd = radiusAnim),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            BottomSheetContent(
                hero = hero,
                infoBoxIconColor = Color(parseColor(vibrant)),
                sheetBackgroundColor = Color(parseColor(darkVibrant)),
                contentColor = Color(parseColor(onDarkVibrant))
            )
        }
    ) {
        ScreenBackground(
            heroImage = hero.image,
            imageFraction = currentSheetFraction,
            backgroundColor = Color(parseColor(darkVibrant)),
            closeDetailsScreen = {
                navController.popBackStack()
            }
        )
    }
}

val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction: Float = bottomSheetState.progress.fraction
        val targetValue: BottomSheetValue = bottomSheetState.targetValue
        val currentValue: BottomSheetValue = bottomSheetState.currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed -> 1f
            currentValue == Expanded && targetValue == Expanded -> 0f
            currentValue == Collapsed && targetValue == Expanded -> 1f - fraction
            currentValue == Expanded && targetValue == Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DetailsScreenPreview() {
    BorutoHeroesTheme {
        DetailsScreen(
            navController = rememberNavController()
        )
    }
}