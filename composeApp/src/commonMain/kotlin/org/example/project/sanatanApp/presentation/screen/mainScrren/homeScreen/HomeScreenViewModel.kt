package org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel():ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.OnSearchQueryChange -> {
                _uiState.value = _uiState.value.copy(searchQuery = action.query)
            }
            is HomeScreenAction.OnSearch -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
//                getRestaurantAndFood(
//                    searchQuery = action.query
//                )
            }
        }
    }
}