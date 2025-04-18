package org.example.project.sanatanApp.domain.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.sanatanApp.domain.model.Aarti

interface AartiRepo {
    suspend fun getAllAarti(): Result<List<Aarti>,DataError.Remote>
    suspend fun getAartiByName(name:String): Result<Aarti,DataError.Remote>
}