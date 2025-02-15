package org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen

import org.example.project.core.presentation.UiText

data class KathaScreenState(
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
)
