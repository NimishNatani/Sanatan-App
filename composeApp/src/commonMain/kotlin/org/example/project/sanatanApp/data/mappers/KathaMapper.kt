package org.example.project.sanatanApp.data.mappers

import org.example.project.sanatanApp.data.dto.BhajanDto
import org.example.project.sanatanApp.data.dto.KathaDto
import org.example.project.sanatanApp.domain.model.Bhajan
import org.example.project.sanatanApp.domain.model.Katha

fun KathaDto.toKatha(): Katha {
    return Katha(
        _id = _id,
        name = name,
        katha = katha.mapValues { (_, linkDto) -> linkDto.toLink() }  // Ensure this returns a proper Map
    )
}