package com.tuan.borutoheroes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.ui.theme.EACH_STAR_SPACING
import com.tuan.borutoheroes.util.Constants.DECIMAL_NUMBER_SEPARATOR
import com.tuan.borutoheroes.util.Constants.EMPTY_STARS
import com.tuan.borutoheroes.util.Constants.FILLED_STARS
import com.tuan.borutoheroes.util.Constants.HALF_FILLED_STARS
import com.tuan.borutoheroes.util.Constants.MAX_RATING
import com.tuan.borutoheroes.util.Constants.MAX_STARS
import com.tuan.borutoheroes.util.Constants.MIN_RATING
import com.tuan.borutoheroes.util.Constants.NO_STARS
import com.tuan.borutoheroes.util.Constants.STAR_SCALE_FACTOR

/**
 * Show a hero's power rating
 *
 * @param modifier
 * @param rating a hero's power rating
 * @param scaleFactor scaling the star inside the canvas
 * @param eachStarSpacing spacing between each star in the widget
 */
@Composable
fun RatingWidget(
    modifier: Modifier = Modifier,
    rating: Double,
    scaleFactor: Float = STAR_SCALE_FACTOR,
    eachStarSpacing: Dp = EACH_STAR_SPACING
) {

    val starPathString = stringResource(id = R.string.star_path)
    val starPath: Path = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }

    val noOfEachStar: Map<String, Int> = calculateStars(rating = rating)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(eachStarSpacing)
    ) {
        noOfEachStar[FILLED_STARS]?.let { numberOfStars ->
            repeat(numberOfStars) {
                FilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor,
                    emptyStar = false
                )
            }
        }
        noOfEachStar[HALF_FILLED_STARS]?.let { numberOfStars ->
            repeat(numberOfStars) {
                HalfFilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
        noOfEachStar[EMPTY_STARS]?.let { numberOfStars ->
            repeat(numberOfStars) {
                FilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor,
                    emptyStar = true
                )
            }
        }
    }
}

/**
 * Calculate how many filled stars, half-filled stars and empty stars to be displayed, base
 * on a hero's rating
 *
 * @param rating hero's rating
 * @return mapping each type of star to the quantity correspondingly
 */
@Composable
private fun calculateStars(rating: Double): Map<String, Int> {

    val maxStars by remember { mutableStateOf(value = MAX_STARS) }
    var filledStars: Int by remember { mutableStateOf(value = NO_STARS) }
    var halfFilledStars: Int by remember { mutableStateOf(value = NO_STARS) }
    var emptyStars: Int by remember { mutableStateOf(value = NO_STARS) }

    // re-calculate filledStars AND halfFilledStars when rating is changed
    LaunchedEffect(key1 = rating) {
        // invalid rating range
        if (rating > MAX_RATING || rating < MIN_RATING) {
            filledStars = NO_STARS
            halfFilledStars = NO_STARS
            return@LaunchedEffect
        }

        // 4.5 -> (4 , 5)
        val (wholeNumberPart, decimalPart) = rating.toString()
            .split(DECIMAL_NUMBER_SEPARATOR)
            .map { it.toInt() }

        when (wholeNumberPart) {
            MAX_STARS -> {
                filledStars = MAX_STARS
                halfFilledStars = NO_STARS
            }
            else -> {
                filledStars = wholeNumberPart

                when (decimalPart) {
                    in 1..5 -> halfFilledStars++
                    in 6..9 -> filledStars++
                }
            }
        }

    }

    emptyStars = maxStars - (filledStars + halfFilledStars)
    return mapOf(
        FILLED_STARS to filledStars,
        HALF_FILLED_STARS to halfFilledStars,
        EMPTY_STARS to emptyStars
    )
}

@Preview(showBackground = true)
@Composable
private fun RatingWidgetPreview() {
    RatingWidget(
        rating = 3.3
    )
}