package org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen

import org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen.AartiScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction

sealed interface MantraScreenAction {
    data class OnSearchQueryChange(val query: String): MantraScreenAction
    data class OnSearch(val query:String): MantraScreenAction
    data object OnLoadingMantra : MantraScreenAction

}