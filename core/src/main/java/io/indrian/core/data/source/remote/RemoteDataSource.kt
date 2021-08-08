package io.indrian.core.data.source.remote

import io.indrian.core.data.source.remote.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getGamesReleased() = flow {
        emit(apiService.getGamesReleased())
    }.flowOn(dispatcher)

    suspend fun getGamesRating() = flow {
        emit(apiService.getGamesRating())
    }.flowOn(dispatcher)

    suspend fun searchGames(search: String) = flow {
        emit(apiService.searchGames(search))
    }.flowOn(dispatcher)

    suspend fun getGameDetails(id: Int) = flow {
        emit(apiService.getGameDetails(id))
    }.flowOn(dispatcher)

    suspend fun getGenres() = flow {
        emit(apiService.getGenres())
    }.flowOn(dispatcher)
}