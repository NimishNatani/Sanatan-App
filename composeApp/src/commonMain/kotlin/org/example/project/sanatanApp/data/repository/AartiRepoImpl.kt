package org.example.project.sanatanApp.data.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.sanatanApp.data.api.AartiApi
import org.example.project.sanatanApp.data.mappers.toAarti
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.repository.AartiRepo

class AartiRepoImpl(val apiService: AartiApi): AartiRepo  {
    override suspend fun getAllAarti(): Result<List<Aarti>, DataError.Remote> {
        return apiService.getAllAarti().map { aartiDtoList ->
            aartiDtoList.map { aartiDto ->
                aartiDto.toAarti()
            }
        }
    }

    override suspend fun getAartiByName(): Aarti {
        TODO("Not yet implemented")
    }

}