package com.tuan.borutoheroes.data.repository

import com.tuan.borutoheroes.data.local.BorutoDatabase
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.domain.repository.LocalDataSource

class LocalDataSourceImpl(
    db: BorutoDatabase
) : LocalDataSource {

    private val dao = db.heroDao

    override suspend fun getHero(heroId: Int): Hero {
        return dao.getHero(heroId = heroId)
    }
}