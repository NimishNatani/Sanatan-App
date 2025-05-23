package org.example.project.sanatanApp.presentation.screen.mainScrren.mantraScreen

import org.example.project.core.presentation.UiText
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Mantra

data class MantraScreenState(
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val mantra : Mantra? = null)
