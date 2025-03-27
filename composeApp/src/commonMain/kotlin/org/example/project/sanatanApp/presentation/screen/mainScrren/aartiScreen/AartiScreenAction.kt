package org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen

import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction

sealed interface AartiScreenAction {
    data class OnSearchQueryChange(val query: String): AartiScreenAction
    data class OnSearch(val query:String): AartiScreenAction
    data object OnLoadingAarti : AartiScreenAction
}