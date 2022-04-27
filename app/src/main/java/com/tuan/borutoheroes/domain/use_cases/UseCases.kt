package com.tuan.borutoheroes.domain.use_cases

import com.tuan.borutoheroes.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.tuan.borutoheroes.domain.use_cases.get_hero.GetHeroUseCase
import com.tuan.borutoheroes.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.tuan.borutoheroes.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.tuan.borutoheroes.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getHeroUseCase: GetHeroUseCase
)
