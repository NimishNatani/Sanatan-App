package org.example.project.sanatanApp.domain.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.domain.model.Mantra

interface MantraRepo {
    suspend fun getAllMantra(): Result<List<Mantra>, DataError.Remote>
    suspend fun getMantraByName(name:String): Result<Mantra, DataError.Remote>
}