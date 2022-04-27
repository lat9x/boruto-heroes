package com.tuan.borutoheroes.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.tuan.borutoheroes.util.Constants.BLACK_COLOR
import com.tuan.borutoheroes.util.Constants.DARK_VIBRANT_COLOR
import com.tuan.borutoheroes.util.Constants.HEX_ANNOTATOR
import com.tuan.borutoheroes.util.Constants.ON_DARK_VIBRANT_COLOR
import com.tuan.borutoheroes.util.Constants.VIBRANT_COLOR
import com.tuan.borutoheroes.util.Constants.WHITE_COLOR

object PaletteGenerator {

    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context = context)
            .data(data = imageUrl)
            .allowHardware(enable = false)
            .build()
        val imageResult = loader.execute(request = request)

        return if (imageResult is SuccessResult) {
            (imageResult.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }

    fun extractColorFromBitmap(bitmap: Bitmap): Map<String, String> {
        return mapOf(
            VIBRANT_COLOR to parseColorSwatch(
                color = Palette.from(bitmap).generate().vibrantSwatch
            ),
            DARK_VIBRANT_COLOR to parseColorSwatch(
                color = Palette.from(bitmap).generate().darkVibrantSwatch
            ),
            ON_DARK_VIBRANT_COLOR to parseBodyColor(
                color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
            )
        )
    }

    private fun parseColorSwatch(color: Palette.Swatch?): String {
        return if (color != null) {
            val parseColor = Integer.toHexString(color.rgb)
            "$HEX_ANNOTATOR$parseColor"
        } else {
            BLACK_COLOR
        }
    }

    private fun parseBodyColor(color: Int?): String {
        return if (color != null) {
            val parseColor = Integer.toHexString(color)
            "$HEX_ANNOTATOR$parseColor"
        } else {
            WHITE_COLOR
        }
    }
}