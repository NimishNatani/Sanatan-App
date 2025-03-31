package org.example.project.sanatanApp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LinkDto(
    val link:String,
    val tb:String = ""
)
