package com.tuan.borutoheroes.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tuan.borutoheroes.data.remote.BorutoApi
import com.tuan.borutoheroes.domain.model.ApiResponse
import com.tuan.borutoheroes.domain.model.Hero
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val borutoApi: BorutoApi,
    private val searchQuery: String
) : PagingSource<Int, Hero>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val apiResponse: ApiResponse = borutoApi.searchHeroes(name = searchQuery)
            val heroes: List<Hero> = apiResponse.heroes

            if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }
}