package com.tuan.borutoheroes.domain.use_cases.get_hero

import com.tuan.borutoheroes.data.repository.Repository
import com.tuan.borutoheroes.domain.model.Hero

class GetHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Hero = repository.getHero(heroId = heroId)
}