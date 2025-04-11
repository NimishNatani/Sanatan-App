package org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen

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
import org.example.project.sanatanApp.domain.repository.MantraRepo
import org.example.project.sanatanApp.presentation.components.PlatformConfiguration
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenState

class MantraScreenViewModel(private val repo : MantraRepo,private val screenSize: PlatformConfiguration) :ViewModel(){

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

            is MantraScreenAction.OnLoadingMantra -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
                getAllMantra()
            }
        }
    }

    private fun getAllMantra() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllMantra()
                .onSuccess {result-> _uiState.update { it.copy(mantraList = result, isLoading = false) } }
                .onError { error-> _uiState.update { it.copy(errorMessage = error.toString(), isLoading = false) } }
        }
    }
    fun getScreenSize():Pair<Float,Float>{
        return Pair(screenSize.screenWidth(),screenSize.screenHeight())
    }
}