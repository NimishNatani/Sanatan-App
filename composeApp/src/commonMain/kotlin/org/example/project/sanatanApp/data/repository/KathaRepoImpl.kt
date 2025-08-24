package org.example.project.sanatanApp.data.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.sanatanApp.data.api.KathaApi
import org.example.project.sanatanApp.data.mappers.toBhajan
import org.example.project.sanatanApp.data.mappers.toKatha
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.domain.model.Katha
import org.example.project.sanatanApp.domain.repository.KathaRepo

class KathaRepoImpl(private val apiService: KathaApi): KathaRepo {
    override suspend fun getAllKatha(): Result<List<Katha>, DataError.Remote> {
        return apiService.getAllKatha().map { kathaDtoList ->
            kathaDtoList.map { kathaDto ->
                kathaDto.toKatha()
            }
        }
    }

    override suspend fun getKathaByName(name: String): Result<Katha, DataError.Remote> {
        return apiService.getKathaByName(name).map { kathaDto ->
            kathaDto.toKatha()
        }
    }

    override suspend fun getAllKathaKalakar(): Result<List<Katha>, DataError.Remote> {
        return apiService.getAllKathaKalakar().map { kathaDtoList  ->
            kathaDtoList.map { kathaDto ->
                kathaDto.toKatha()
            }
        }
    }

    override suspend fun getKathaKalakarByName(name: String): Result<Katha, DataError.Remote> {
        return apiService.getKathaKalakarByName(name).map { kathaDto ->
            kathaDto.toKatha()
        }
    }
}