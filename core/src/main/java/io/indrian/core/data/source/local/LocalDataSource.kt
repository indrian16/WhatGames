package io.indrian.core.data.source.local

import io.indrian.core.data.source.local.dao.GameDao
import io.indrian.core.data.source.local.dao.GenreDao
import io.indrian.core.data.source.local.entity.GameEntity
import io.indrian.core.data.source.local.entity.GenreEntity
import kotlinx.coroutines.flow.first
import timber.log.Timber

class LocalDataSource(
    private val gameDao: GameDao,
    private val genreDao: GenreDao
) {

    fun getGamesReleased() = gameDao.getGamesReleased()
    fun getGamesRating() = gameDao.getGamesRating()
    fun getFavoriteGames() = gameDao.getFavoriteGames()
    fun getDetailsGame(id: Int) = gameDao.getDetailsGame(id)

    suspend fun insertGames(games: List<GameEntity>) = gameDao.insertGames(games)
    suspend fun insertGame(game: GameEntity) = gameDao.insertGame(game)
    suspend fun setFavoriteGame(id: Int) {
        val currentGame: GameEntity? = getDetailsGame(id).first()
        if (currentGame != null) {
            currentGame.isFavorite = !currentGame.isFavorite
            Timber.d("setFavoriteGame: $currentGame")
            gameDao.updateFavoriteGame(currentGame)
        }
    }

    fun getGenres() = genreDao.getGenres()
    // Soon! fun getGenreById(id: Int) = appDatabase.genreDao().getGenreById(id)
    suspend fun insertGenres(genres: List<GenreEntity>) = genreDao.insertGenres(genres)
}