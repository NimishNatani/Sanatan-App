package org.example.project.sanatanApp.data.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.sanatanApp.data.api.AartiApi
import org.example.project.sanatanApp.data.api.MantraApi
import org.example.project.sanatanApp.data.mappers.toAarti
import org.example.project.sanatanApp.data.mappers.toMantra
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Mantra
import org.example.project.sanatanApp.domain.repository.AartiRepo
import org.example.project.sanatanApp.domain.repository.MantraRepo

class MantraRepoImpl(private val apiService: MantraApi): MantraRepo {
    override suspend fun getAllMantra(): Result<List<Mantra>, DataError.Remote> {
        return apiService.getAllMantra().map { mantraDtoList ->
            mantraDtoList.map { mantraDto ->
                mantraDto.toMantra()
            }
        }
    }

    override suspend fun getMantraByName(): Mantra {
        TODO("Not yet implemented")
    }

}