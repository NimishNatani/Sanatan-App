package org.example.project.sanatanApp.presentation.screen.mainScrren.youtubeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.example.project.core.domain.onError
import org.example.project.core.domain.onSuccess
import org.example.project.sanatanApp.domain.repository.YoutubeRepo

class YoutubeScreenViewModel(private val repo: YoutubeRepo) : ViewModel() {

    private val _uiState = MutableStateFlow(YoutubeScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: YoutubeScreenAction) {
        when (action) {
            is YoutubeScreenAction.OnLoadingSubtitles -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
                getSubtitles(action.aarti)
            }

        }
    }

    private fun getSubtitles(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAartiSubtitles(url).onSuccess { result ->
                _uiState.update {
                    it.copy(
                        youtube = result,
                        isLoading = false
                    )
                }
            }.onError { error -> _uiState.update { it.copy(errorMessage = error.toString(), isLoading = false) } }
        }
    }



}

@Serializable
data class ErrorData(
    val status: String,
    val message: String
)

fun parseStringFromSubtitles(subtitles: String): ErrorData? {
    return try {
        Json.decodeFromString<ErrorData>(subtitles)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

