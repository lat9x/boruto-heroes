package com.tuan.borutoheroes.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    val allHeroes: Flow<PagingData<Hero>> = useCases.getAllHeroesUseCase()
}