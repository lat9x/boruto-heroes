package com.tuan.borutoheroes.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tuan.borutoheroes.R

/**
 * Holds onBoardingPage information
 *
 * @param image image that represented a single page
 * @param title title of a page (from string resource)
 * @param description description of a page (from string resource)
 */
sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
) {
    object First : OnBoardingPage(
        image = R.drawable.greetings,
        title = R.string.greetings,
        description = R.string.greetings_description
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = R.string.explore,
        description = R.string.explore_description
    )

    object Last : OnBoardingPage(
        image = R.drawable.power,
        title = R.string.power,
        description = R.string.power_description
    )
}