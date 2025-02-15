package org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreenState

class BhajanScreenViewModel():ViewModel() {

    private val _uiState = MutableStateFlow(BhajanScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: BhajanScreenAction) {
        when (action) {
            is BhajanScreenAction.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = action.query) }
            }
            is BhajanScreenAction.OnSearch -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
            }
        }
    }
}