package org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen

import org.example.project.core.presentation.UiText

data class MantraScreenState(
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
)
