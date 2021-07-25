package io.indrian.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.indrian.core.data.source.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface GenreDao {
    @Query("SELECT * FROM genres ORDER BY games_count ASC")
    fun getGenres(): Flow<List<GenreEntity>>

    @Query("SELECT * FROM genres WHERE id = :id")
    fun getGenreById(id: Int): Flow<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)
}