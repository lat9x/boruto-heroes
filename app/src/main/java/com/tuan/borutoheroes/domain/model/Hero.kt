package com.tuan.borutoheroes.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tuan.borutoheroes.util.Constants.TBL_HERO
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TBL_HERO)
data class Hero(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    @ColumnInfo(name = "nature_types") val natureTypes: List<String>
)
