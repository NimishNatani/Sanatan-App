package org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen

import org.example.project.core.presentation.UiText

data class BhajanScreenState(
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
)
