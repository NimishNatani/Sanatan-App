package org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen

import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenAction

sealed interface KathaScreenAction {
    data class OnSearchQueryChange(val query: String): KathaScreenAction
    data class OnSearch(val query:String): KathaScreenAction
}