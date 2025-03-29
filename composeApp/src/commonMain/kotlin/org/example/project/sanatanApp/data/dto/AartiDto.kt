package org.example.project.sanatanApp.data.dto

import kotlinx.serialization.Serializable
import org.example.project.sanatanApp.domain.model.Link
@Serializable
data class AartiDto(
    val _id :String,
    val name: String,
    val aarti:Map<String, LinkDto>
)

