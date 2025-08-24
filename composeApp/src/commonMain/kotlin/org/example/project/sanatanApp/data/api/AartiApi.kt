package org.example.project.sanatanApp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import org.example.project.core.domain.Result
import org.example.project.core.di.safeCall
import org.example.project.core.domain.DataError
import org.example.project.sanatanApp.SharedObject.baseUrl
import org.example.project.sanatanApp.data.dto.AartiDto
import org.example.project.sanatanApp.domain.model.Aarti

class AartiApi(private val client: HttpClient) {
    private val BASE_URL = "$baseUrl/aarti"

    suspend fun getAllAarti(): Result<List<AartiDto>,DataError.Remote> {
        return safeCall<List<AartiDto>> {
            client.get("${BASE_URL}/allAarti") {

            }
        }
    }

    suspend fun getAartiByName(name:String): Result<AartiDto,DataError.Remote> {
        return safeCall<AartiDto>{
            client.get("${BASE_URL}/$name") {

            }
        }
    }

}