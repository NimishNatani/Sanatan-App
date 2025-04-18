package org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen

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
import org.example.project.sanatanApp.domain.repository.BhajanRepo
import org.example.project.sanatanApp.presentation.components.PlatformConfiguration
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreenState

class BhajanScreenViewModel(private val repo:BhajanRepo,private val screenSize: PlatformConfiguration):ViewModel() {

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
            is BhajanScreenAction.OnLoadingBhajan -> {
                _uiState.value = _uiState.value.copy(isLoading = false)
                getBhajanByName(action.name)
            }

            is BhajanScreenAction.OnLoadingBhajanKalakar -> {
                _uiState.value = _uiState.value.copy(isLoading = false)
                getBhajanKalakarByName(action.name)
            }
        }
    }

    private fun getBhajanByName(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getBhajanByName(name)
                .onSuccess {result-> _uiState.update { it.copy(bhajan = result, isLoading = false) } }
                .onError { error-> _uiState.update { it.copy(errorMessage = error.toString(), isLoading = false) } }
        }
    }
    private fun getBhajanKalakarByName(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getBhajanKalakarByName(name)
                .onSuccess {result-> _uiState.update { it.copy(bhajanKalakar =  result, isLoading = false) } }
                .onError { error-> _uiState.update { it.copy(errorMessage = error.toString(), isLoading = false) } }
        }
    }
    fun getScreenSize():Pair<Float,Float>{
        return Pair(screenSize.screenWidth(),screenSize.screenHeight())
    }
}