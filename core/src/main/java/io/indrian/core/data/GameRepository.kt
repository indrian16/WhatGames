package io.indrian.core.data

import io.indrian.core.data.source.local.LocalDataSource
import io.indrian.core.data.source.local.entity.GameEntity
import io.indrian.core.data.source.remote.RemoteDataSource
import io.indrian.core.data.source.remote.network.ApiResponse
import io.indrian.core.data.source.remote.response.GameDetailsResponse
import io.indrian.core.data.source.remote.response.GameResponse
import io.indrian.core.data.source.remote.response.GenreResponse
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.model.Genre
import io.indrian.core.domain.repository.IGameRepository
import io.indrian.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IGameRepository {

    override fun getGamesReleased(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                val genres = localDataSource.getGenres()
                return localDataSource.getGamesReleased().map {
                    DataMapper.mapEntitiesToDomain(it, genres.first())
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> = remoteDataSource.getGamesReleased()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                data.forEach {
                    localDataSource.insertGenres(
                        DataMapper.mapGenreResponseToEntities(
                            it.genreResponses
                        )
                    )
                }

                val entities = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGames(
                    entities.map {
                        it.copy(
                            ordering = "released",
                            isFavorite = localDataSource.getDetailsGame(it.id).first()?.isFavorite ?: false
                        )
                    }
                )
            }
        }.asFlow()

    override fun getGamesRating(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                val genres = localDataSource.getGenres()
                return localDataSource.getGamesRating().map {
                    DataMapper.mapEntitiesToDomain(it, genres.first())
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> = remoteDataSource.getGamesRating()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                data.forEach {
                    localDataSource.insertGenres(
                        DataMapper.mapGenreResponseToEntities(
                            it.genreResponses
                        )
                    )
                }

                val entities = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGames(
                    entities.map {
                        it.copy(
                            ordering = "rating",
                            isFavorite = localDataSource.getDetailsGame(it.id).first()?.isFavorite ?: false
                        )
                    }
                )
            }
        }.asFlow()

    override fun getDetailsGames(id: Int): Flow<Resource<Game>> =
        object : NetworkBoundResource<Game, GameDetailsResponse>() {
            override fun loadFromDB(): Flow<Game> {
                val genres = localDataSource.getGenres()
                return localDataSource.getDetailsGame(id).map {
                    DataMapper.mapEntityToDomain(it ?: GameEntity(), genres.first())
                }
            }

            override fun shouldFetch(data: Game?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<GameDetailsResponse>> = remoteDataSource.getGameDetails(id)

            override suspend fun saveCallResult(data: GameDetailsResponse) {
                localDataSource.insertGenres(
                    DataMapper.mapGenreResponseToEntities(
                        data.genres
                    )
                )

                val entity = DataMapper.mapResponseToEntity(data)
                localDataSource.insertGame(
                    entity.copy(
                        ordering = localDataSource.getDetailsGame(id).first()?.ordering ?: "",
                        isFavorite = localDataSource.getDetailsGame(id).first()?.isFavorite ?: false
                    )
                )
            }
        }.asFlow()

    override fun searchGames(search: String): Flow<Resource<List<Game>>> =
        object : OnlyNetworkBoundResource<List<Game>, List<GameResponse>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> = remoteDataSource.searchGames(search)

            override suspend fun resultCall(data: List<GameResponse>): List<Game> {
                data.forEach {
                    localDataSource.insertGenres(
                        DataMapper.mapGenreResponseToEntities(
                            it.genreResponses
                        )
                    )
                }

                val entities = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGames(
                    entities.map {
                        it.copy(
                            isFavorite = localDataSource.getDetailsGame(it.id).first()?.isFavorite ?: false
                        )
                    }
                )
                return DataMapper.mapResponseToDomain(data)
            }
        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it, listOf())
        }
    }

    override suspend fun setFavoriteGame(id: Int) {
        localDataSource.setFavoriteGame(id)
    }

    override fun getGenres(): Flow<Resource<List<Genre>>> =
        object : NetworkBoundResource<List<Genre>, List<GenreResponse>>() {
            override fun loadFromDB(): Flow<List<Genre>> {
                return localDataSource.getGenres().map {
                    DataMapper.mapGenreEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Genre>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GenreResponse>>> = remoteDataSource.getGenres()

            override suspend fun saveCallResult(data: List<GenreResponse>) {
                val entities = DataMapper.mapGenreResponseToEntities(data)
                localDataSource.insertGenres(entities)
            }
        }.asFlow()
}