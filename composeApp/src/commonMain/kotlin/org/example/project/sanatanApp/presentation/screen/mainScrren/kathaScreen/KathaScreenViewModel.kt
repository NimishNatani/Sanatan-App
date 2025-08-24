package org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen

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
import org.example.project.sanatanApp.domain.repository.KathaRepo
import org.example.project.sanatanApp.presentation.components.PlatformConfiguration
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenState
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenAction

class KathaScreenViewModel(private val repo: KathaRepo, private val screenSize: PlatformConfiguration):ViewModel() {

    private val _uiState = MutableStateFlow(KathaScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: KathaScreenAction) {
        when (action) {
            is KathaScreenAction.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = action.query) }
            }
            is KathaScreenAction.OnSearch -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
            }is KathaScreenAction.OnLoadingKatha -> {
            _uiState.value = _uiState.value.copy(isLoading = false)
            getKathaByName(action.name)
        }

            is KathaScreenAction.OnLoadingKathaKalakar -> {
                _uiState.value = _uiState.value.copy(isLoading = false)
                getKathaKalakarByName(action.name)
            }
        }
    }

    private fun getKathaByName(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getKathaByName(name)
                .onSuccess {result-> _uiState.update { it.copy(katha = result, isLoading = false) } }
                .onError { error-> _uiState.update { it.copy(errorMessage = error.toString(), isLoading = false) } }
        }
    }
    private fun getKathaKalakarByName(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getKathaKalakarByName(name)
                .onSuccess {result-> _uiState.update { it.copy(kathaKalakar =  result, isLoading = false) } }
                .onError { error-> _uiState.update { it.copy(errorMessage = error.toString(), isLoading = false) } }
        }
    }
    fun getScreenSize():Pair<Float,Float>{
        return Pair(screenSize.screenWidth(),screenSize.screenHeight())
    }
}