package com.tuan.borutoheroes.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.tuan.borutoheroes.data.repository.Repository
import com.tuan.borutoheroes.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}