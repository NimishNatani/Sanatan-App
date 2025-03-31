package org.example.project.sanatanApp.data.mappers

import org.example.project.sanatanApp.data.dto.AartiDto
import org.example.project.sanatanApp.data.dto.BhajanDto
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Bhajan

fun BhajanDto.toBhajan(): Bhajan {
    return Bhajan(
        _id = _id,
        name = name,
        bhajan = bhajan.mapValues { (_, linkDto) -> linkDto.toLink() }  // Ensure this returns a proper Map
    )
}