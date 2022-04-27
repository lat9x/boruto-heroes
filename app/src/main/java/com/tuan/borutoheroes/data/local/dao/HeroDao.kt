package com.tuan.borutoheroes.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tuan.borutoheroes.domain.model.Hero

@Dao
interface HeroDao {

    /**
     * Get all heroes from the boruto database
     *
     * @return PagingSource<PageNumber, Hero> supports asynchronous loading snapshot of data,
     * retrieve all items from db in a paginated way (page by page)
     */
    @Query("SELECT * FROM tbl_hero ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM tbl_hero WHERE id = :heroId")
    suspend fun getHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes: List<Hero>)

    @Query("DELETE FROM tbl_hero")
    suspend fun deleteAllHeroes()
}