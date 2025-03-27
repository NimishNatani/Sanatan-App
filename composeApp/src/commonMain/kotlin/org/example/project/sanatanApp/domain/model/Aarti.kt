package org.example.project.sanatanApp.domain.model

data class Aarti(
    val _id :String,
    val name: String,
    val aarti:Map<String,Link>
)
