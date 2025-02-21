package org.example.project.sanatanApp.presentation.screen.mainScrren.aartiScreen

import org.example.project.core.presentation.UiText

data class AartiScreenState(
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
)
