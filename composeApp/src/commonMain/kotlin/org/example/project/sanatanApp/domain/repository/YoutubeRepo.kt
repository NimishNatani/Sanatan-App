package org.example.project.sanatanApp.domain.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.sanatanApp.domain.model.Youtube

interface YoutubeRepo {
    suspend fun getAartiSubtitles(url:String): Result<Youtube, DataError.Remote>

}