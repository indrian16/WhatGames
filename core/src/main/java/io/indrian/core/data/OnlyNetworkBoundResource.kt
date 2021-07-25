package io.indrian.core.data

import io.indrian.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class OnlyNetworkBoundResource<ResultType, RequestType> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        when (val apiResponse = createCall().first()) {

            is ApiResponse.Success -> {
                val result = resultCall(apiResponse.data)
                emit(
                    Resource.Success(result)
                )
            }
            is ApiResponse.Error-> {
                onFetchFailed()
                Resource.Error<ResultType>(
                    apiResponse.errorMessage
                )
            }

        }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun resultCall(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> = result
}