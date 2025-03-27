package org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.onError
import org.example.project.core.domain.onSuccess
import org.example.project.sanatanApp.domain.repository.AartiRepo

class AartiScreenViewModel(private val repo: AartiRepo) : ViewModel() {

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

            is AartiScreenAction.OnLoadingAarti -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
                getAllAarti()
            }
        }
    }

    private fun getAllAarti() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllAarti()
                .onSuccess { _uiState.update { it.copy(aartiList = it.aartiList, isLoading = false) } }
                .onError { _uiState.update { it.copy(errorMessage = it.errorMessage, isLoading = false) } }
        }
    }
}