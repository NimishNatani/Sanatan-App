package org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen

import org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen.HomeScreenAction

sealed interface BhajanScreenAction {

    data class OnSearchQueryChange(val query: String): BhajanScreenAction
    data class OnSearch(val query:String): BhajanScreenAction
    data object OnLoadingBhajan : BhajanScreenAction
    data object OnLoadingBhajanKalakar : BhajanScreenAction
}