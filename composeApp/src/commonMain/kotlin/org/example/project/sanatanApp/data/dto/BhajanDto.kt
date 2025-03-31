package org.example.project.sanatanApp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BhajanDto(
    val _id :String,
    val name: String,
    val bhajan:Map<String, LinkDto>
)
