package com.tuan.borutoheroes.domain.repository

import com.tuan.borutoheroes.domain.model.Hero

interface LocalDataSource {
    suspend fun getHero(heroId: Int): Hero
}