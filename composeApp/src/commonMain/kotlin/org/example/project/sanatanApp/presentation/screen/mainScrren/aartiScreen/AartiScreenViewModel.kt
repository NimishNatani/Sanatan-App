package org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenState

class AartiScreenViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AartiScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: AartiScreenAction) {
        when (action) {
            is AartiScreenAction.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = action.query) }
            }
            is AartiScreenAction.OnSearch -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
            }
        }
    }
}