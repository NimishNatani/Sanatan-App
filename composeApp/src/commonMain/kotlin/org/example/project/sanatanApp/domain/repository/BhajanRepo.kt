package org.example.project.sanatanApp.domain.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Bhajan

interface BhajanRepo {
    suspend fun getAllBhajan(): Result<List<Bhajan>, DataError.Remote>
    suspend fun getBhajanByName(): Bhajan
}