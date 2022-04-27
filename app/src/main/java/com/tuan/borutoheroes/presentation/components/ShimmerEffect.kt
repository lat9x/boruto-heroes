package com.tuan.borutoheroes.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import com.tuan.borutoheroes.ui.theme.*
import com.tuan.borutoheroes.util.Constants.ABOUT_PLACE_HOLDER_LINES
import com.tuan.borutoheroes.util.Constants.MAX_STARS
import com.tuan.borutoheroes.util.Constants.PLACEHOLDER_ITEMS
import com.tuan.borutoheroes.util.Constants.SHIMMER_DURATION
import com.tuan.borutoheroes.util.Constants.START_ALPHA_VALUE
import com.tuan.borutoheroes.util.Constants.TARGET_ALPHA_VALUE

@Composable
fun ShimmerEffect() {
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(space = SMALL_PADDING)
    ) {
        items(count = PLACEHOLDER_ITEMS) {
            AnimatedShimmerItem()
        }
    }
}

@Composable
private fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = START_ALPHA_VALUE,
        targetValue = TARGET_ALPHA_VALUE,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = SHIMMER_DURATION,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    ShimmerItem(alpha = alphaAnim)
}

@Composable
private fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = HERO_ITEM_HEIGHT),
        color = MaterialTheme.colors.shimmerEffectItemColor,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ) {
        Column(
            modifier = Modifier.padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            ShimmerNamePlaceholder(alpha = alpha)
            Spacer(modifier = Modifier.height(height = SMALL_PADDING))
            ShimmerAboutPlaceholder(alpha = alpha)
            ShimmerRatingPlaceholder(alpha = alpha)
        }
    }
}

@Composable
private fun ShimmerNamePlaceholder(alpha: Float) {
    Surface(
        modifier = Modifier
            .alpha(alpha = alpha)
            .fillMaxWidth(fraction = 0.5f)
            .height(height = SHIMMER_NAME_PLACEHOLDER_HEIGHT),
        color = MaterialTheme.colors.shimmerEffectPlaceholderColor,
        shape = RoundedCornerShape(size = SMALL_PADDING)
    ) {
        /* NO-CONTENT*/
    }
}

@Composable
private fun ShimmerAboutPlaceholder(alpha: Float) {
    repeat(ABOUT_PLACE_HOLDER_LINES) {
        Surface(
            modifier = Modifier
                .alpha(alpha = alpha)
                .fillMaxWidth()
                .height(height = SHIMMER_ABOUT_PLACEHOLDER_HEIGHT),
            color = MaterialTheme.colors.shimmerEffectPlaceholderColor,
            shape = RoundedCornerShape(size = SMALL_PADDING)
        ) {
            /* NO-CONTENT*/
        }
        Spacer(modifier = Modifier.height(height = EXTRA_SMALL_PADDING))
    }
}

@Composable
private fun ShimmerRatingPlaceholder(alpha: Float) {
    Row(modifier = Modifier.fillMaxWidth()) {
        repeat(MAX_STARS) {
            Surface(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .size(size = SHIMMER_RATING_PLACEHOLDER_SIZE),
                color = MaterialTheme.colors.shimmerEffectPlaceholderColor,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) {
                /* NO-CONTENT*/
            }
            Spacer(modifier = Modifier.width(width = SMALL_PADDING))
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun ShimmerItemPreview() {
    BorutoHeroesTheme {
        AnimatedShimmerItem()
    }
}