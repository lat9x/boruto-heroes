package com.tuan.borutoheroes.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuan.borutoheroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    fun saveOnBoardingState(isCompleted: Boolean) {
        viewModelScope.launch {
           useCases.saveOnBoardingUseCase(isCompleted = isCompleted)
        }
    }
}