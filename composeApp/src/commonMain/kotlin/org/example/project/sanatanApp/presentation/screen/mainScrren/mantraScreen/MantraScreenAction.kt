package org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen


sealed interface MantraScreenAction {
    data class OnSearchQueryChange(val query: String): MantraScreenAction
    data class OnSearch(val query:String): MantraScreenAction
    data class OnLoadingMantra(val name:String) : MantraScreenAction
}