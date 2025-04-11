package org.example.project.sanatanApp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeDto(
    val errorMessage:String,
    val subtitles:String,
    val title:String
)
