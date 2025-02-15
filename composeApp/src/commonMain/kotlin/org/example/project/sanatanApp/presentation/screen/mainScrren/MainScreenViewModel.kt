package org.example.project.sanatanApp.presentation.screen.mainScrren

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel():ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: MainScreenAction){
        when(action){
            is MainScreenAction.OnTabSelected -> {
                _uiState.update {  it.copy(selectedTabIndex = BottomNavItem(action.index,action.icon,action.name))}
            }

        }

    }
}