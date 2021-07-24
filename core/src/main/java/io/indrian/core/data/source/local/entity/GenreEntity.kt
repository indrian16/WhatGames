package io.indrian.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.indrian.core.utils.CoreUtils

@Entity(tableName = CoreUtils.TABLE_GENRES)
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "games_count")
    val gamesCount: Int = 0,
    @ColumnInfo(name = "image_background")
    val imageBackground: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
)