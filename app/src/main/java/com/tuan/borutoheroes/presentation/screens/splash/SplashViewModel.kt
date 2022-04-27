package com.tuan.borutoheroes.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuan.borutoheroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _onBoardingState = MutableStateFlow(value = false)
    val onBoardingState: StateFlow<Boolean> = _onBoardingState

    init {
        viewModelScope.launch {
            _onBoardingState.value =
                useCases.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }
}