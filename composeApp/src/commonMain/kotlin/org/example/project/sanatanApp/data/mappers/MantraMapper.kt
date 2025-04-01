package org.example.project.sanatanApp.data.mappers

import org.example.project.sanatanApp.data.dto.AartiDto
import org.example.project.sanatanApp.data.dto.MantraDto
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Mantra

fun MantraDto.toMantra(): Mantra {
    return Mantra(
        _id = _id,
        name = name,
        mantra = mantra.mapValues { (_, linkDto) -> linkDto.toLink() }  // Ensure this returns a proper Map
    )
}