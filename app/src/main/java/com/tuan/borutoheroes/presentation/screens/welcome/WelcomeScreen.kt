package com.tuan.borutoheroes.presentation.screens.welcome

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.domain.model.OnBoardingPage
import com.tuan.borutoheroes.navigation.Screen
import com.tuan.borutoheroes.ui.theme.*
import com.tuan.borutoheroes.util.Constants.TOTAL_ON_BOARDING_PAGES

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages: List<OnBoardingPage> = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Last
    )

    // remember the pager state (default is the first page)
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {
        // pager
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            count = TOTAL_ON_BOARDING_PAGES,
            verticalAlignment = Alignment.Top
        ) { pageNumber ->
            OnBoardingPageContent(pages[pageNumber])
        }

        // indicator
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = MaterialTheme.colors.activeIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )

        FinishButton(
            modifier = Modifier
                .weight(1f),
            pagerState = pagerState,
            onClick = {
                navController.popBackStack()
                navController.navigate(route = Screen.Home.route)
                viewModel.saveOnBoardingState(isCompleted = true)
            }
        )
    }
}

@Composable
private fun OnBoardingPageContent(
    page: OnBoardingPage
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = page.image),
            contentDescription = stringResource(id = page.title)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = page.title),
            color = MaterialTheme.colors.welcomeScreenTitleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = stringResource(id = page.description),
            color = MaterialTheme.colors.welcomeScreenDescriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun FinishButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = (pagerState.currentPage == (TOTAL_ON_BOARDING_PAGES - 1))
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.finish_on_boarding_pages))
            }
        }
    }
}

@Preview(
    name = "First Page Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "First Page Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OnBoardingFirstPagePreview() {
    BorutoHeroesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
        ) {
            OnBoardingPageContent(
                page = OnBoardingPage.First
            )
        }
    }
}

@Preview(
    name = "Second Page Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Second Page Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OnBoardingSecondPagePreview() {
    BorutoHeroesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
        ) {
            OnBoardingPageContent(
                page = OnBoardingPage.Second
            )
        }
    }
}

@Preview(
    name = "Last Page Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Last Page Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OnBoardingLastPagePreview() {
    BorutoHeroesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
        ) {
            OnBoardingPageContent(
                page = OnBoardingPage.Last
            )
        }
    }
}