package org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenState
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenAction

class KathaScreenViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(KathaScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: KathaScreenAction) {
        when (action) {
            is KathaScreenAction.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = action.query) }
            }
            is KathaScreenAction.OnSearch -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
            }
        }
    }
}