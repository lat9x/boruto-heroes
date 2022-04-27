package com.tuan.borutoheroes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tuan.borutoheroes.data.local.dao.HeroDao
import com.tuan.borutoheroes.data.local.dao.HeroRemoteKeysDao
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.domain.model.HeroRemoteKeys

@Database(
    entities = [Hero::class, HeroRemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {
    abstract val heroDao: HeroDao
    abstract val heroRemoteKeysDao: HeroRemoteKeysDao
}