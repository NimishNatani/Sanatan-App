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
import org.example.project.sanatanApp.presentation.components.PlatformConfiguration

class AartiScreenViewModel(private val repo: AartiRepo,private val screenSize: PlatformConfiguration) : ViewModel() {

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
                getAartiByName(action.name)
            }
        }
    }



    private fun getAartiByName(name:String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAartiByName(name)
                .onSuccess {result-> _uiState.update { it.copy(aarti = result, isLoading = false) } }
                .onError { error-> _uiState.update { it.copy(errorMessage = error.toString(), isLoading = false) } }
        }
    }

    fun getScreenSize():Pair<Float,Float>{
        return Pair(screenSize.screenWidth(),screenSize.screenHeight())
    }
}