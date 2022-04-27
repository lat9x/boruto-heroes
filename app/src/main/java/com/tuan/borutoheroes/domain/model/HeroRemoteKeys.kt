package com.tuan.borutoheroes.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tuan.borutoheroes.util.Constants.TBL_HERO_REMOTE_KEYS

/**
 * Paginate data and cache it in local db properly from API -> use RemoteMediator. RemoteMediator
 * will need to use these remotes key in order to paginate through the data properly
 *
 * @param id id of a hero
 * @param prevPage the previous page associated with that hero
 * @param nextPage the next page associated with that hero
 */
@Entity(tableName = TBL_HERO_REMOTE_KEYS)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "prev_page") val prevPage: Int?,
    @ColumnInfo(name = "next_page") val nextPage: Int?,
    @ColumnInfo(name = "last_updated") val lastUpdated: Long?
)
