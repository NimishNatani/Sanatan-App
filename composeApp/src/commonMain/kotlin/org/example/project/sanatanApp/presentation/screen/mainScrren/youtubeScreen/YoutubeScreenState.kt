package org.example.project.sanatanApp.presentation.screen.mainScrren.youtubeScreen

import org.example.project.core.presentation.UiText
import org.example.project.sanatanApp.domain.model.Youtube

data class YoutubeScreenState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val youtube: Youtube?= null,

)
