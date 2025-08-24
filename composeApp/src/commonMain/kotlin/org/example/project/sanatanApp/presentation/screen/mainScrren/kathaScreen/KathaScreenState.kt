package org.example.project.sanatanApp.presentation.screen.mainScrren.kathaScreen

import org.example.project.core.presentation.UiText
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.domain.model.Katha

data class KathaScreenState(
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val errorMessage: String? = null,

    val katha : Katha? = null,
    val kathaKalakar : Katha? = null
)
