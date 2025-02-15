package org.example.project.sanatanApp.presentation.screen.mainScrren.homeScreen

import org.example.project.core.presentation.UiText

data class HomeScreenState(
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
    var screenState:String = "Home",
//    val searchResults: SearchItem = SearchItem(),
)
