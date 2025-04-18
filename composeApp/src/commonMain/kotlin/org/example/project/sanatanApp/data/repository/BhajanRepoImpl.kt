package org.example.project.sanatanApp.data.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.sanatanApp.data.api.BhajanApi
import org.example.project.sanatanApp.data.mappers.toBhajan
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.domain.repository.BhajanRepo

class BhajanRepoImpl(private val apiService: BhajanApi) : BhajanRepo {
    override suspend fun getAllBhajan(): Result<List<Bhajan>, DataError.Remote> {
        return apiService.getAllBhajan().map { bhajanDtoList ->
            bhajanDtoList.map { bhajanDto ->
                bhajanDto.toBhajan()
            }
        }
    }

    override suspend fun getBhajanByName(name: String): Result<Bhajan, DataError.Remote> {
        return apiService.getBhajanByName().map { bhajanDto ->
            bhajanDto.toBhajan()
        }
    }

    override suspend fun getAllBhajanKalakar(): Result<List<Bhajan>, DataError.Remote> {
        return apiService.getAllBhajanKalakar().map { bhajanDtoList ->
            bhajanDtoList.map { bhajanDto ->
                bhajanDto.toBhajan()
            }
        }
    }

    override suspend fun getBhajanKalakarByName(name: String): Result<Bhajan, DataError.Remote> {
        return apiService.getBhajanKalakarByName().map { bhajanDto ->
            bhajanDto.toBhajan()
        }
    }
}