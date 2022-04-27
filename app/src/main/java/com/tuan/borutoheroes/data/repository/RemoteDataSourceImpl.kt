package com.tuan.borutoheroes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.tuan.borutoheroes.data.local.BorutoDatabase
import com.tuan.borutoheroes.data.paging_source.HeroRemoteMediator
import com.tuan.borutoheroes.data.paging_source.SearchHeroesSource
import com.tuan.borutoheroes.data.remote.BorutoApi
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.domain.repository.RemoteDataSource
import com.tuan.borutoheroes.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

/**
 * Injection happens in NetworkModule
 */
class RemoteDataSourceImpl constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSource {

    private val heroDao = borutoDatabase.heroDao

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory: () -> PagingSource<Int, Hero> = {
            heroDao.getAllHeroes()
        }

        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE
            ),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(name: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(
                    borutoApi = borutoApi,
                    searchQuery = name
                )
            }
        ).flow
    }
}