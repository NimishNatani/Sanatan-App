package org.example.project.sanatanApp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.example.project.core.di.safeCall
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.sanatanApp.SharedObject.baseUrl
import org.example.project.sanatanApp.data.dto.KathaDto

class KathaApi(private val client: HttpClient) {
    private val BASE_URL = "$baseUrl/katha"

    suspend fun getAllKatha(): Result<List<KathaDto>, DataError.Remote> {
        return safeCall<List<KathaDto>> {
            client.get("${BASE_URL}/allKatha") {

            }
        }
    }

    suspend fun getAllKathaKalakar(): Result<List<KathaDto>, DataError.Remote> {
        return safeCall<List<KathaDto>> {
            client.get("${BASE_URL}/allKathaKalakar") {

            }
        }
    }

    suspend fun getKathaByName(name:String): Result<KathaDto, DataError.Remote> {
        return safeCall<KathaDto> {
            client.get("${BASE_URL}/$name") {

            }
        }
    }

    suspend fun getKathaKalakarByName(name:String): Result<KathaDto, DataError.Remote> {
        return safeCall<KathaDto> {
            client.get("${BASE_URL}/kalakar/$name") {

            }
        }
    }

}