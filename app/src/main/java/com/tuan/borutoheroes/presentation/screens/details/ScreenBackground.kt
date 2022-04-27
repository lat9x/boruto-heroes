package com.tuan.borutoheroes.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.ui.theme.INFO_ICON_SIZE
import com.tuan.borutoheroes.ui.theme.SMALL_PADDING
import com.tuan.borutoheroes.util.Constants
import com.tuan.borutoheroes.util.Constants.MIN_HERO_IMAGE_FRACTION

@Composable
fun ScreenBackground(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    closeDetailsScreen: () -> Unit
) {
    val imageUrl = "${Constants.LOCAL_HOST_BASE_URL}$heroImage"
    val painter = rememberImagePainter(data = imageUrl) {
        error(drawableResId = R.drawable.ic_placeholder)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_HERO_IMAGE_FRACTION)
                .align(alignment = Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = closeDetailsScreen
            ) {
                Icon(
                    modifier = Modifier.size(size = INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_details_screen),
                    tint = Color.White
                )
            }
        }
    }
}