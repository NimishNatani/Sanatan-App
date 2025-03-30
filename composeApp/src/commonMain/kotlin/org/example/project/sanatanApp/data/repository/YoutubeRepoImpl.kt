package org.example.project.sanatanApp.data.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.sanatanApp.data.api.AartiApi
import org.example.project.sanatanApp.data.api.YoutubeApi
import org.example.project.sanatanApp.data.mappers.toYoutube
import org.example.project.sanatanApp.domain.model.Youtube
import org.example.project.sanatanApp.domain.repository.YoutubeRepo

class YoutubeRepoImpl(private val apiService: YoutubeApi):YoutubeRepo {
    override suspend fun getAartiSubtitles(url: String): Result<Youtube, DataError.Remote> {
        return apiService.getAartiSubtitles(url).map { youtubeDto -> youtubeDto.toYoutube() }
    }
}