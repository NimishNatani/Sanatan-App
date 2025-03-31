package org.example.project.sanatanApp.presentation.screen.mainScrren.bhajanScreen

import org.example.project.core.presentation.UiText
import org.example.project.sanatanApp.domain.model.Bhajan

data class BhajanScreenState(
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val bhajanList : List<Bhajan> = emptyList()
)
