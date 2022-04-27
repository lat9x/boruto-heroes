package com.tuan.borutoheroes.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.domain.use_cases.UseCases
import com.tuan.borutoheroes.util.Constants.DETAILS_SCREEN_ARGS_HERO_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _hero = MutableStateFlow<Hero?>(value = null)
    val hero: StateFlow<Hero?> = _hero

    init {
        viewModelScope.launch {
            val heroId: Int? = savedStateHandle.get<Int>(DETAILS_SCREEN_ARGS_HERO_ID)
            _hero.value = heroId?.let {
                useCases.getHeroUseCase(heroId = it)
            }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(emptyMap())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun generateColorPalette() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }
}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}