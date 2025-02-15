package org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen

import org.example.project.sanatanApp.presentation.screen.mainScrren.MainScreenAction

sealed interface HomeScreenAction {

     data class OnSearchQueryChange(val query: String): HomeScreenAction
     data class OnSearch(val query:String):HomeScreenAction
     data class OnScreenStateChange(var name:String): HomeScreenAction



 }