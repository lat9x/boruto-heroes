package com.tuan.borutoheroes.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.tuan.borutoheroes.data.repository.Repository
import com.tuan.borutoheroes.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(name = searchQuery)
    }
}