package io.indrian.core.data.source.local

import io.indrian.core.data.source.local.entity.GameEntity
import io.indrian.core.data.source.local.entity.GenreEntity
import io.indrian.core.data.source.local.room.AppDatabase
import kotlinx.coroutines.flow.first
import timber.log.Timber

class LocalDataSource(private val appDatabase: AppDatabase) {

    fun getGamesReleased() = appDatabase.gameDao().getGamesReleased()
    fun getGamesRating() = appDatabase.gameDao().getGamesRating()
    fun getFavoriteGames() = appDatabase.gameDao().getFavoriteGames()
    fun getDetailsGame(id: Int) = appDatabase.gameDao().getDetailsGame(id)

    suspend fun insertGames(games: List<GameEntity>) = appDatabase.gameDao().insertGames(games)
    suspend fun insertGame(game: GameEntity) = appDatabase.gameDao().insertGame(game)
    suspend fun setFavoriteGame(id: Int) {
        val currentGame: GameEntity? = getDetailsGame(id).first()
        if (currentGame != null) {
            currentGame.isFavorite = !currentGame.isFavorite
            Timber.d("setFavoriteGame: $currentGame")
            appDatabase.gameDao().updateFavoriteGame(currentGame)
        }
    }

    fun getGenres() = appDatabase.genreDao().getGenres()
    // Soon! fun getGenreById(id: Int) = appDatabase.genreDao().getGenreById(id)
    suspend fun insertGenres(genres: List<GenreEntity>) = appDatabase.genreDao().insertGenres(genres)
}