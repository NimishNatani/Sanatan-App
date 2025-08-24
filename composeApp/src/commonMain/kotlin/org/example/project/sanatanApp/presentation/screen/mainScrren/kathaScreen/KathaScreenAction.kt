package org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen

import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction
import org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen.MantraScreenAction

sealed interface KathaScreenAction {
    data class OnSearchQueryChange(val query: String): KathaScreenAction
    data class OnSearch(val query:String): KathaScreenAction

    data class OnLoadingKatha(val name:String) : KathaScreenAction
    data class OnLoadingKathaKalakar(val name:String) : KathaScreenAction
}