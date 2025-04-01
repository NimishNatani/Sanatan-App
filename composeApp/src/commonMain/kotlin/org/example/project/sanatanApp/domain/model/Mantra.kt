package org.example.project.sanatanApp.domain.model

import org.example.project.sanatanApp.data.dto.LinkDto

data class Mantra(
    val _id :String,
    val name: String,
    val mantra:Map<String, Link>
)
