package com.tuan.borutoheroes.data.remote

import com.tuan.borutoheroes.domain.model.ApiResponse
import com.tuan.borutoheroes.util.Constants.API_ALL_HEROES_PATH
import com.tuan.borutoheroes.util.Constants.API_QUERY_ALL_HEROES_PAGE
import com.tuan.borutoheroes.util.Constants.API_QUERY_SEARCH_HEROES_NAME
import com.tuan.borutoheroes.util.Constants.API_SEARCH_HEROES_PATH
import com.tuan.borutoheroes.util.Constants.FIRST_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET(API_ALL_HEROES_PATH)
    suspend fun getAllHeroes(
        @Query(API_QUERY_ALL_HEROES_PAGE) page: Int = FIRST_PAGE
    ): ApiResponse

    @GET(API_SEARCH_HEROES_PATH)
    suspend fun searchHeroes(
        @Query(API_QUERY_SEARCH_HEROES_NAME) name: String
    ): ApiResponse
}