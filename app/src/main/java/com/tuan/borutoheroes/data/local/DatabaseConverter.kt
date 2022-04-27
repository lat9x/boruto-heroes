package com.tuan.borutoheroes.data.local

import androidx.room.TypeConverter

/**
 * This class tells Room how to Store a list of strings.
 *
 * Example:
 * List: ["hello", "there", "how"] <--> String: "hello,there,how"
 */
class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list) {
            stringBuilder.append(item).append(separator)
        }

        // delete the final separator
        stringBuilder.setLength(stringBuilder.length - separator.length)

        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }
}