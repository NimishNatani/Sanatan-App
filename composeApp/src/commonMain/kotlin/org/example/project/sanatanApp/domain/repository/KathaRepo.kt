package org.example.project.sanatanApp.domain.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.domain.model.Katha

interface KathaRepo {
    suspend fun getAllKatha(): Result<List<Katha>, DataError.Remote>
    suspend fun getKathaByName(name:String): Result<Katha, DataError.Remote>
    suspend fun getAllKathaKalakar(): Result<List<Katha>, DataError.Remote>
    suspend fun getKathaKalakarByName(name: String): Result<Katha, DataError.Remote>
}