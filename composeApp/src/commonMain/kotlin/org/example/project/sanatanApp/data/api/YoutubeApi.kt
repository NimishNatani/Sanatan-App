package org.example.project.sanatanApp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import org.example.project.core.domain.Result
import org.example.project.core.di.safeCall
import org.example.project.core.domain.DataError
import org.example.project.sanatanApp.data.dto.AartiDto
import org.example.project.sanatanApp.data.dto.YoutubeDto
import org.example.project.sanatanApp.domain.model.Aarti

class YoutubeApi(private val client: HttpClient) {
    private val BASE_URL = "http://10.14.2.168:8080/api/youtube"


    suspend fun getAartiSubtitles(url:String): Result<YoutubeDto,DataError.Remote> {
        val videoId = extractVideoId(url)
        return safeCall<YoutubeDto> {
            client.get("${BASE_URL}/subtitles") {
                url {
                    parameters.append("videoId", videoId)
                }
            }
        }
    }

    private fun extractVideoId(videoUrl: String): String {
        return if ("v=" in videoUrl) {
            videoUrl.substringAfter("v=").substringBefore("&")
        } else {
            videoUrl // If it's already just an ID
        }
    }

}