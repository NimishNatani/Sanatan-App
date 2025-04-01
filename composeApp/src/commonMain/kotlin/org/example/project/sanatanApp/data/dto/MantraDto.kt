package org.example.project.sanatanApp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MantraDto(
    val _id :String,
    val name: String,
    val mantra:Map<String, LinkDto>
)
