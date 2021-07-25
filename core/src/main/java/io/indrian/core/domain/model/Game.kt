package io.indrian.core.domain.model

import java.util.*

data class Game(
    val id: Int = 0,
    val backgroundImage: String? = "",
    val descriptionRaw: String = "",
    val genres: List<Genre> = listOf(),
    val name: String = "",
    val platforms: List<Int> = listOf(),
    val updated: Date = Date(),
    val website: String = "",
    val isFavorite: Boolean = false
)