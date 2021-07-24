package io.indrian.core.data.source

import io.indrian.core.data.NetworkBoundResource
import io.indrian.core.data.Resource
import io.indrian.core.data.source.local.LocalDataSource
import io.indrian.core.data.source.remote.RemoteDataSource
import io.indrian.core.data.source.remote.network.ApiResponse
import io.indrian.core.data.source.remote.response.GameResponse
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.repository.IGameRepository
import io.indrian.core.utils.AppExecutors
import io.indrian.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    override fun getGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> = remoteDataSource.getGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val entities = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGames(entities)
            }
        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val entity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(entity, state) }
    }
}