package org.example.project.sanatanApp.data.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.sanatanApp.data.api.AartiApi
import org.example.project.sanatanApp.data.api.BhajanApi
import org.example.project.sanatanApp.data.mappers.toAarti
import org.example.project.sanatanApp.data.mappers.toBhajan
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.domain.repository.AartiRepo
import org.example.project.sanatanApp.domain.repository.BhajanRepo

class BhajanRepoImpl(private val apiService: BhajanApi): BhajanRepo {
    override suspend fun getAllBhajan(): Result<List<Bhajan>, DataError.Remote> {
        return apiService.getAllBhajan().map { bhajanDtoList ->
            bhajanDtoList.map { bhajanDto ->
                bhajanDto.toBhajan()
            }
        }
    }

    override suspend fun getAllBhajanKalakar(): Result<List<Bhajan>, DataError.Remote> {
        return apiService.getAllBhajanKalakar().map { bhajanDtoList ->
            bhajanDtoList.map { bhajanDto ->
                bhajanDto.toBhajan()
            }
        }
    }

    override suspend fun getBhajanByName(): Bhajan {
        TODO("Not yet implemented")
    }

}