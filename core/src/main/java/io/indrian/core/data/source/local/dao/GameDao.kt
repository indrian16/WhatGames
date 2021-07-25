package io.indrian.core.data.source.local.dao

import androidx.room.*
import io.indrian.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM games WHERE ordering = 'released' ORDER BY updated DESC")
    fun getGamesReleased(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE ordering = 'rating'  ORDER BY updated DESC")
    fun getGamesRating(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE id = :id")
    fun getDetailsGame(id: Int): Flow<GameEntity?>

    @Query("SELECT * FROM games WHERE is_favorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: GameEntity)

    @Update
    suspend fun updateFavoriteGame(gameEntity: GameEntity)
}