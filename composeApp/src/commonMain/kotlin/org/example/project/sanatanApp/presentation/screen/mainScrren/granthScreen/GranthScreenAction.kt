package org.example.project.sanatanApp.presentation.screen.mainScrren.granthScreen

import org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen.BhajanScreenAction

sealed interface GranthScreenAction {
    data class OnSearchQueryChange(val query: String): GranthScreenAction
    data class OnSearch(val query:String): GranthScreenAction
}