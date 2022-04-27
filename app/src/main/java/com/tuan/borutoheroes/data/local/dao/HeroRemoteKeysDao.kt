package com.tuan.borutoheroes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tuan.borutoheroes.domain.model.HeroRemoteKeys

@Dao
interface HeroRemoteKeysDao {

    @Query("SELECT * FROM tbl_hero_remote_keys WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): HeroRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKeys>)

    @Query("DELETE FROM tbl_hero_remote_keys")
    suspend fun deleteAllRemoteKeys()
}