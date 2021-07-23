package io.indrian.core.data.source.remote

import io.indrian.core.data.source.remote.network.ApiResponse
import io.indrian.core.data.source.remote.network.ApiService
import io.indrian.core.data.source.remote.response.GameDetailsResponse
import io.indrian.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getGames(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getGames()
                val results = response.gameResponses
                if (results.isNotEmpty()) {
                    emit(ApiResponse.Success(results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e(e.toString())
            }
        }
    }

    suspend fun searchGames(search: String): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.searchGames(search)
                val results = response.gameResponses
                if (results.isNotEmpty()) {
                    emit(ApiResponse.Success(results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e(e.toString())
            }
        }
    }

    suspend fun getGameDetails(id: Int): Flow<ApiResponse<GameDetailsResponse>> {
        return flow {
            try {
                val response = apiService.getGameDetails(id)
                if (response != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e(e.toString())
            }
        }
    }
}