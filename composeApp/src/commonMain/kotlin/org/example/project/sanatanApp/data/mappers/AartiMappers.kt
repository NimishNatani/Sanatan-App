package org.example.project.sanatanApp.data.mappers

import org.example.project.sanatanApp.data.dto.AartiDto
import org.example.project.sanatanApp.data.dto.LinkDto
import org.example.project.sanatanApp.domain.model.Aarti
import org.example.project.sanatanApp.domain.model.Link

fun AartiDto.toAarti(): Aarti {
    return Aarti(
        _id = _id,
        name = name,
        aarti = aarti.mapValues { (_, linkDto) -> linkDto.toLink() }  // Ensure this returns a proper Map
    )
}

fun LinkDto.toLink(): Link {
    return Link(
        link = link,
        thumbnail = tb
    )

}