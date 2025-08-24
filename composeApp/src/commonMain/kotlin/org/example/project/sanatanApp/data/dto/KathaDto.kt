package org.example.project.sanatanApp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class KathaDto(val _id :String,
                    val name: String,
                    val katha:Map<String, LinkDto>
)
