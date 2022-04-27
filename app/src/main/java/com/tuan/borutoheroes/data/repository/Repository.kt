package com.tuan.borutoheroes.data.repository

import androidx.paging.PagingData
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.domain.repository.DataStoreOperations
import com.tuan.borutoheroes.domain.repository.LocalDataSource
import com.tuan.borutoheroes.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations,
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {

    suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.saveOnBoardingState(isCompleted = isCompleted)
    }

    fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()

    fun getAllHeroes(): Flow<PagingData<Hero>> = remote.getAllHeroes()

    fun searchHeroes(name: String): Flow<PagingData<Hero>> = remote.searchHeroes(name = name)

    suspend fun getHero(heroId: Int): Hero = local.getHero(heroId = heroId)
}