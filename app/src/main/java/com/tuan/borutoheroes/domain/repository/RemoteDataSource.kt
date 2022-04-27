package com.tuan.borutoheroes.domain.repository

import androidx.paging.PagingData
import com.tuan.borutoheroes.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(name: String): Flow<PagingData<Hero>>
}