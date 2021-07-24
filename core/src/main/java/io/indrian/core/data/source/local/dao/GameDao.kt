package io.indrian.core.data.source.local.dao

import androidx.room.*
import io.indrian.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM games WHERE ordering = 'released'")
    fun getGamesReleased(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE ordering = 'rating'")
    fun getGamesRating(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE is_favorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Update
    fun updateFavoriteGame(gameEntity: GameEntity)
}