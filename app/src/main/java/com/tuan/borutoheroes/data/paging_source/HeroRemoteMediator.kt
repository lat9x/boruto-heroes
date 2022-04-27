package com.tuan.borutoheroes.data.paging_source

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.tuan.borutoheroes.data.local.BorutoDatabase
import com.tuan.borutoheroes.data.remote.BorutoApi
import com.tuan.borutoheroes.domain.model.ApiResponse
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.domain.model.HeroRemoteKeys
import com.tuan.borutoheroes.util.Constants.DB_CACHE_TIMEOUT
import com.tuan.borutoheroes.util.Constants.DECREASE_PAGE_BY_ONE
import com.tuan.borutoheroes.util.Constants.DEFAULT_LAST_SERVER_UPDATE_TIME
import com.tuan.borutoheroes.util.Constants.FIRST_PAGE
import com.tuan.borutoheroes.util.Constants.RANDOM_HERO_ID
import com.tuan.borutoheroes.util.Constants.TO_MINUTE
import com.tuan.borutoheroes.util.Constants.TO_SECOND
import javax.inject.Inject

class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteMediator<Int, Hero>() {

    private val heroDao = borutoDatabase.heroDao
    private val heroRemoteKeysDao = borutoDatabase.heroRemoteKeysDao

    /**
     * Check if cached data is out of date, and decide whether to trigger a remote refresh. Runs
     * before any loading is performed.
     */
    override suspend fun initialize(): InitializeAction {
        val currentTime: Long = System.currentTimeMillis()
        val lastUpdated: Long = heroRemoteKeysDao.getRemoteKeys(
            id = RANDOM_HERO_ID
        )?.lastUpdated ?: DEFAULT_LAST_SERVER_UPDATE_TIME

        val diffInMinutes: Int = ((currentTime - lastUpdated) / TO_SECOND / TO_MINUTE).toInt()

        return if (diffInMinutes <= DB_CACHE_TIMEOUT) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {

            // 1. Determine which page to load from the network, depending on the load type & the
            // data that has been loaded so far
            val page: Int = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys: HeroRemoteKeys? = getRemoteKeyClosestToCurrentPosition(
                        pagingState = state
                    )
                    remoteKeys?.nextPage?.minus(other = DECREASE_PAGE_BY_ONE) ?: FIRST_PAGE
                }
                LoadType.PREPEND -> {
                    val remoteKeys: HeroRemoteKeys? = getRemoteKeyForFirstItem(
                        pagingState = state
                    )

                    // if the remoteKeys is null AND the prevPage is null then pagination will end
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = (remoteKeys != null)
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys: HeroRemoteKeys? = getRemoteKeyForLastItem(
                        pagingState = state
                    )

                    // if the remoteKeys is null AND the nextPage is null then pagination will end
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = (remoteKeys != null)
                    )
                    nextPage
                }
            }

            // 2. Trigger the network request
            val response: ApiResponse = borutoApi.getAllHeroes(page = page)

            // 3. Perform actions depending on the outcome of the load operation
            if (response.heroes.isNotEmpty()) {
                // do some database operations
                borutoDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        // clear all tables' data in database
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val keys: List<HeroRemoteKeys> = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = response.prevPage,
                            nextPage = response.nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }

                    // add all response's data to database
                    heroRemoteKeysDao.addAllRemoteKeys(heroRemoteKeys = keys)
                    heroDao.addHeroes(heroes = response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = (response.nextPage == null))
        } catch (e: Exception) {
            MediatorResult.Error(throwable = e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        pagingState: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return pagingState.anchorPosition?.let { position ->
            pagingState.closestItemToPosition(position)?.id?.let { heroId ->
                heroRemoteKeysDao.getRemoteKeys(id = heroId)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        pagingState: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return pagingState.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { hero ->
            heroRemoteKeysDao.getRemoteKeys(id = hero.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        pagingState: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return pagingState.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { hero ->
            heroRemoteKeysDao.getRemoteKeys(id = hero.id)
        }
    }
}