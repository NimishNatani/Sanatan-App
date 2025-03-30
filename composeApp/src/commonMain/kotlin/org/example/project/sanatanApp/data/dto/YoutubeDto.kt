package org.example.project.sanatanApp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeDto(
    val status:String,
    val subtitles:String
)
