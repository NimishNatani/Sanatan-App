package org.example.project.sanatanApp.domain.model

data class Bhajan(
    val _id :String,
    val name: String,
    val bhajan:Map<String,Link>
)
