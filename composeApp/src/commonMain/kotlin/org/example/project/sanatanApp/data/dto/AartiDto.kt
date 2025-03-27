package org.example.project.sanatanApp.data.dto

import org.example.project.sanatanApp.domain.model.Link

data class AartiDto(
    val _id :String,
    val name: String,
    val aarti:Map<String, LinkDto>
)

