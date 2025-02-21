package org.example.project.sanatanApp.presentation.screen.mainScrren.granthScreen

import org.example.project.core.presentation.UiText

data class GranthScreenState(
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
)
