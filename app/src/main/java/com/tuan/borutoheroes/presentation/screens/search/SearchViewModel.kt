package com.tuan.borutoheroes.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.domain.use_cases.UseCases
import com.tuan.borutoheroes.util.Constants.EMPTY_SEARCH_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _searchQuery = mutableStateOf(value = EMPTY_SEARCH_QUERY)
    val searchQuery: State<String> = _searchQuery

    private val _searchResult = MutableStateFlow<PagingData<Hero>>(PagingData.empty())
    val searchResult: StateFlow<PagingData<Hero>> = _searchResult

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun searchHeroes(searchQuery: String) {
        viewModelScope.launch {
            useCases.searchHeroesUseCase(
                searchQuery = searchQuery
            ).cachedIn(viewModelScope).collect { result ->
                _searchResult.value = result
            }
        }
    }
}