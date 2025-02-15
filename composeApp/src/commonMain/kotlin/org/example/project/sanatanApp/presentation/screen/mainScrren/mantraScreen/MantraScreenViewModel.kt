package org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenState

class MantraScreenViewModel :ViewModel(){

    private val _uiState = MutableStateFlow(MantraScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: MantraScreenAction) {
        when (action) {
            is MantraScreenAction.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = action.query) }
            }
            is MantraScreenAction.OnSearch -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
            }
        }
    }
}