package io.indrian.core.data.source.local

import io.indrian.core.data.source.local.entity.GameEntity
import io.indrian.core.data.source.local.entity.GenreEntity
import io.indrian.core.data.source.local.entity.PlatformEntity
import io.indrian.core.data.source.local.room.AppDatabase

class LocalDataSource(private val appDatabase: AppDatabase) {

    fun getGamesReleased() = appDatabase.gameDao().getGamesReleased()
    fun getGamesRating() = appDatabase.gameDao().getGamesRating()
    fun getFavoriteGames() = appDatabase.gameDao().getFavoriteGames()
    fun getDetailsGame(id: Int) = appDatabase.gameDao().getDetailsGame(id)

    suspend fun insertGames(games: List<GameEntity>) = appDatabase.gameDao().insertGames(games)
    suspend fun insertGame(game: GameEntity) = appDatabase.gameDao().insertGame(game)
    fun setFavoriteGame(gameEntity: GameEntity, newState: Boolean) {
        gameEntity.isFavorite = newState
        appDatabase.gameDao().updateFavoriteGame(gameEntity)
    }

    fun getGenres() = appDatabase.genreDao().getGenres()
    fun getGenreById(id: Int) = appDatabase.genreDao().getGenreById(id)
    suspend fun insertGenres(genres: List<GenreEntity>) = appDatabase.genreDao().insertGenres(genres)

    fun getPlatforms() = appDatabase.platformDao().getPlatforms()
    fun getPlatformById(id: Int) = appDatabase.platformDao().getPlatformById(id)
    suspend fun insertPlatform(platform: PlatformEntity) = appDatabase.platformDao().insertPlatform(platform)
}