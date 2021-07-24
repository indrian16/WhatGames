package io.indrian.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.indrian.core.utils.CoreUtils
import java.util.*

@Entity(tableName = CoreUtils.TABLE_GAMES)
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "background_image")
    val backgroundImage: String = "",
    @ColumnInfo(name = "description_raw")
    val descriptionRaw: String = "",
    @ColumnInfo(name = "genres")
    val genres: List<Int> = listOf(),
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "platforms")
    val platforms: List<Int> = listOf(),
    @ColumnInfo(name = "updated")
    val updated: Date = Date(),
    @ColumnInfo(name = "website")
    val website: String = "",
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)
