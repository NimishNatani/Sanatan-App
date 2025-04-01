package org.example.project.sanatanApp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import org.example.project.core.domain.Result
import org.example.project.core.di.safeCall
import org.example.project.core.domain.DataError
import org.example.project.sanatanApp.data.dto.AartiDto
import org.example.project.sanatanApp.data.dto.BhajanDto
import org.example.project.sanatanApp.domain.model.Aarti

class BhajanApi(private val client: HttpClient) {
    private val BASE_URL = "http://10.14.2.168:8080/api/bhajan"

    suspend fun getAllBhajan(): Result<List<BhajanDto>,DataError.Remote> {
        return safeCall<List<BhajanDto>> {
            client.get("${BASE_URL}/allBhajan") {

            }
        }
    }

    suspend fun getAllBhajanKalakar(): Result<List<BhajanDto>,DataError.Remote> {
        return safeCall<List<BhajanDto>> {
            client.get("${BASE_URL}/allBhajanKalakar") {

            }
        }
    }

}