package org.example.project.sanatanApp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import org.example.project.core.domain.Result
import org.example.project.core.di.safeCall
import org.example.project.core.domain.DataError
import org.example.project.sanatanApp.data.dto.AartiDto
import org.example.project.sanatanApp.data.dto.MantraDto
import org.example.project.sanatanApp.domain.model.Aarti

class MantraApi(private val client: HttpClient) {
    private val BASE_URL = "http://10.14.1.72:8080/api/mantra"

    suspend fun getAllMantra(): Result<List<MantraDto>,DataError.Remote> {
        return safeCall<List<MantraDto>> {
            client.get("${BASE_URL}/allMantra") {

            }
        }
    }

}