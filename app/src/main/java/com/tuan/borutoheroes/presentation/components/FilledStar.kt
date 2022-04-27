package com.tuan.borutoheroes.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.ui.theme.DRAW_STAR_CANVAS_SIZE
import com.tuan.borutoheroes.ui.theme.LightGray
import com.tuan.borutoheroes.ui.theme.StarColor
import com.tuan.borutoheroes.util.Constants.DECREASE_ALPHA_BY_HALF
import com.tuan.borutoheroes.util.Constants.DIVIDED_BY_HALF
import com.tuan.borutoheroes.util.Constants.SHRINK_STAR_SIZE
import com.tuan.borutoheroes.util.Constants.STAR_SCALE_FACTOR

/**
 * Draw a filled star
 *
 * @param starPath path to draw the star
 * @param starPathBounds star's boundary in form of a rectangle
 * @param scaleFactor scaling the star inside the canvas
 * @param emptyStar is the star empty or not
 */
@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float,
    emptyStar: Boolean
) {
    Canvas(modifier = Modifier.size(size = DRAW_STAR_CANVAS_SIZE)) {
        val canvasSize = size

        // scaling the star size
        scale(scale = scaleFactor) {

            // get width and height of the star
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height

            // calculate to put star in the middle of the canvas
            val left = (canvasSize.width / DIVIDED_BY_HALF) - (pathWidth / SHRINK_STAR_SIZE)
            val top = (canvasSize.height / DIVIDED_BY_HALF) - (pathHeight / SHRINK_STAR_SIZE)

            // move top-left to center of the canvas
            translate(left = left, top = top) {
                // draw star
                drawPath(
                    path = starPath,
                    color = if (emptyStar)
                        LightGray.copy(alpha = DECREASE_ALPHA_BY_HALF)
                    else
                        StarColor
                )
            }
        }
    }
}

@Preview(
    name = "Filled Star",
    showBackground = true
)
@Composable
private fun FilledStarPreview() {
    val starPath: Path = PathParser().parsePathString(
        pathData = stringResource(id = R.string.star_path)
    ).toPath()

    val starPathBounds = starPath.getBounds()

    FilledStar(
        starPath = starPath,
        starPathBounds = starPathBounds,
        scaleFactor = STAR_SCALE_FACTOR,
        emptyStar = false
    )
}

@Preview(
    name = "Empty Star",
    showBackground = true
)
@Composable
private fun EmptyStarPreview() {
    val starPath: Path = PathParser().parsePathString(
        pathData = stringResource(id = R.string.star_path)
    ).toPath()

    val starPathBounds = starPath.getBounds()

    FilledStar(
        starPath = starPath,
        starPathBounds = starPathBounds,
        scaleFactor = STAR_SCALE_FACTOR,
        emptyStar = true
    )
}