package org.example.project.sanatanApp.presentation.screen.mainScrren.granthScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenState

class GranthScreenViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(GranthScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: GranthScreenAction) {
        when (action) {
            is GranthScreenAction.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = action.query) }
            }
            is GranthScreenAction.OnSearch -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
            }
        }
    }
}