package org.example.project.sanatanApp.data.mappers

import org.example.project.sanatanApp.data.dto.YoutubeDto
import org.example.project.sanatanApp.domain.model.Youtube

fun YoutubeDto.toYoutube(): Youtube {
    return Youtube(
        errorMessage = errorMessage,
        subtitles = subtitles,
        title = title,
    )
}