package io.indrian.core.data.source.remote

import com.haroldadmin.cnradapter.NetworkResponse
import io.indrian.core.data.source.remote.network.ApiResponse
import io.indrian.core.data.source.remote.network.ApiService
import io.indrian.core.data.source.remote.response.GameDetailsResponse
import io.indrian.core.data.source.remote.response.GameResponse
import io.indrian.core.data.source.remote.response.GenreResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getGamesReleased(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            emit(
                when (val response = apiService.getGamesReleased()) {
                    is NetworkResponse.Success -> {
                        val results = response.body.gameResponses
                        if (results.isNotEmpty()) {
                            ApiResponse.Success(response.body.gameResponses)
                        } else {
                            ApiResponse.Empty
                        }
                    }
                    is NetworkResponse.ServerError -> {
                        ApiResponse.Error(response.body?.error ?: "ServerError")
                    }
                    is NetworkResponse.NetworkError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                    is NetworkResponse.UnknownError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                }
            )
        }.flowOn(dispatcher)
    }

    suspend fun getGamesRating(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            emit(
                when (val response = apiService.getGamesRating()) {
                    is NetworkResponse.Success -> {
                        val results = response.body.gameResponses
                        if (results.isNotEmpty()) {
                            ApiResponse.Success(response.body.gameResponses)
                        } else {
                            ApiResponse.Empty
                        }
                    }
                    is NetworkResponse.ServerError -> {
                        ApiResponse.Error(response.body?.error ?: "ServerError")
                    }
                    is NetworkResponse.NetworkError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                    is NetworkResponse.UnknownError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                }
            )
        }.flowOn(dispatcher)
    }

    suspend fun searchGames(search: String): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            emit(
                when (val response = apiService.searchGames(search)) {
                    is NetworkResponse.Success -> {
                        val results = response.body.gameResponses
                        if (results.isNotEmpty()) {
                            ApiResponse.Success(response.body.gameResponses)
                        } else {
                            ApiResponse.Empty
                        }
                    }
                    is NetworkResponse.ServerError -> {
                        ApiResponse.Error(response.body?.error ?: "ServerError")
                    }
                    is NetworkResponse.NetworkError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                    is NetworkResponse.UnknownError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                }
            )
        }.flowOn(dispatcher)
    }

    suspend fun getGameDetails(id: Int): Flow<ApiResponse<GameDetailsResponse>> {
        return flow {
            emit(
                when (val response = apiService.getGameDetails(id)) {
                    is NetworkResponse.Success -> {
                        ApiResponse.Success(response.body)
                    }
                    is NetworkResponse.ServerError -> {
                        ApiResponse.Error(response.body?.error ?: "ServerError")
                    }
                    is NetworkResponse.NetworkError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                    is NetworkResponse.UnknownError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                }
            )
        }.flowOn(dispatcher)
    }

    suspend fun getGenres(): Flow<ApiResponse<List<GenreResponse>>> {
        return flow {
            emit(
                when (val response = apiService.getGenres()) {
                    is NetworkResponse.Success -> {
                        val results = response.body.results
                        if (results.isNotEmpty()) {
                            ApiResponse.Success(response.body.results)
                        } else {
                            ApiResponse.Empty
                        }
                    }
                    is NetworkResponse.ServerError -> {
                        ApiResponse.Error(response.body?.error ?: "ServerError")
                    }
                    is NetworkResponse.NetworkError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                    is NetworkResponse.UnknownError -> {
                        ApiResponse.Error(response.error.message ?: "ServerError")
                    }
                }
            )
        }.flowOn(dispatcher)
    }
}