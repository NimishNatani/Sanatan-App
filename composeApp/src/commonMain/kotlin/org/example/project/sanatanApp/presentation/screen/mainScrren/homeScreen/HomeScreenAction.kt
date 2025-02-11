package org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen

 sealed interface HomeScreenAction {

     data class OnSearchQueryChange(val query: String): HomeScreenAction
     data class OnSearch(val query:String):HomeScreenAction


 }