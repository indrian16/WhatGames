package io.indrian.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.indrian.core.data.source.local.dao.GameDao
import io.indrian.core.data.source.local.dao.GenreDao
import io.indrian.core.data.source.local.entity.GameEntity
import io.indrian.core.data.source.local.entity.GenreEntity
import io.indrian.core.utils.CoreUtils

@Database(
    entities = [GameEntity::class, GenreEntity::class],
    version = CoreUtils.DB_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun genreDao(): GenreDao
}