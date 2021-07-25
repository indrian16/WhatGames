package io.indrian.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val id: Int = 0,
    val gamesCount: Int = 0,
    val imageBackground: String = "",
    val name: String = "",
): Parcelable