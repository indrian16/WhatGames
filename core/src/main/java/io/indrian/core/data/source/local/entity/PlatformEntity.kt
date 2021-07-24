package io.indrian.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.indrian.core.utils.CoreUtils

@Entity(tableName = CoreUtils.TABLE_PLATFORM)
data class PlatformEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = ""
)