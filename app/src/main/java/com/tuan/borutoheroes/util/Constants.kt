package com.tuan.borutoheroes.util

object Constants {
    const val SPLASH_SCREEN_ROUTE = "splash"
    const val WELCOME_SCREEN_ROUTE = "welcome"
    const val HOME_SCREEN_ROUTE = "home"
    const val DETAILS_SCREEN_ROUTE_PRE = "details"
    const val DETAILS_SCREEN_ARGS_HERO_ID = "heroId"
    const val DETAILS_SCREEN_ROUTE = "$DETAILS_SCREEN_ROUTE_PRE/{$DETAILS_SCREEN_ARGS_HERO_ID}"
    const val SEARCH_SCREEN_ROUTE = "search"

    const val TBL_HERO = "tbl_hero"
    const val TBL_HERO_REMOTE_KEYS = "tbl_hero_remote_keys"
    const val DB_NAME = "hero_database"

    const val NO_ROTATION = 0f
    const val FULL_ROTATION = 360f
    const val LOGO_ANIMATION_DURATION_TIME = 1000
    const val LOGO_ANIMATION_DELAY_TIME = 200

    const val TOTAL_ON_BOARDING_PAGES = 3
    const val PREFERENCES_NAME = "boruto_preferences"
    const val PREFERENCES_KEY = "on_boarding_completed"

    // physical device address
    const val LOCAL_HOST_BASE_URL = "http://192.168.0.104:8080"
    const val CLIENT_TIMEOUT = 15L
    const val DEFAULT_LAST_SERVER_UPDATE_TIME = 0L
    const val DB_CACHE_TIMEOUT = 1440 // 1 day in minutes

    const val API_ALL_HEROES_PATH = "/boruto/heroes"
    const val API_SEARCH_HEROES_PATH = "$API_ALL_HEROES_PATH/search"
    const val API_QUERY_ALL_HEROES_PAGE = "page"
    const val FIRST_PAGE = 1
    const val API_QUERY_SEARCH_HEROES_NAME = "name"

    const val ITEMS_PER_PAGE = 3
    const val DECREASE_PAGE_BY_ONE = 1

    /* ---------------------------- Star Widget ---------------------------- */
    const val DIVIDED_BY_HALF = 2f
    const val SHRINK_STAR_SIZE = 1.7f
    const val STAR_SCALE_FACTOR = 2f
    const val DECREASE_ALPHA_BY_HALF = 0.5f

    const val MAX_STARS = 5
    const val NO_STARS = 0
    const val MAX_RATING = 5.0
    const val MIN_RATING = 0.0
    const val DECIMAL_NUMBER_SEPARATOR = "."

    const val FILLED_STARS = "filledStars"
    const val HALF_FILLED_STARS = "halfFilledStars"
    const val EMPTY_STARS = "emptyStars"

    /* ---------------------------- Shimmer Effect ---------------------------- */
    const val ABOUT_PLACE_HOLDER_LINES = 3
    const val START_ALPHA_VALUE = 1f
    const val TARGET_ALPHA_VALUE = 0f
    const val SHIMMER_DURATION = 500
    const val PLACEHOLDER_ITEMS = 2

    /* ---------------------------- Others ---------------------------- */
    const val RANDOM_HERO_ID = 1
    const val TO_SECOND = 1000
    const val TO_MINUTE = 60

    const val EMPTY_SEARCH_QUERY = ""
    const val NO_HEROES_FOUND = 0

    const val MIN_HERO_IMAGE_FRACTION = 0.4f
    const val ABOUT_TEXT_MAX_LINES = 7

    const val VIBRANT_COLOR = "vibrant"
    const val DARK_VIBRANT_COLOR = "darkVibrant"
    const val ON_DARK_VIBRANT_COLOR = "onDarkVibrant"
    const val HEX_ANNOTATOR = "#"
    const val BLACK_COLOR = "#000000"
    const val WHITE_COLOR = "#FFFFFF"
}