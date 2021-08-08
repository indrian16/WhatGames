package io.indrian.core.data

import com.haroldadmin.cnradapter.NetworkResponse
import io.indrian.core.data.source.remote.response.ErrorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import timber.log.Timber

abstract class OnlyNetworkBoundResource<ResultType, RequestType : Any, ErrorType: Any> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        when (val apiResponse = createCall().first()) {

            is NetworkResponse.Success -> {
                val result = resultCall(apiResponse.body)
                emit(
                    Resource.Success(result)
                )
            }
            is NetworkResponse.ServerError -> {
                onFetchFailed()
                Timber.e("NetworkResponse.ServerError(${apiResponse.body})")

                // Specific error with data class
                when (apiResponse.body) {
                    is ErrorResponse -> {
                        val error = (apiResponse.body as ErrorResponse).error
                        emit(
                            Resource.Error<ResultType>(error, null)
                        )
                    }
                }
            }

        }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<NetworkResponse<RequestType, ErrorType>>

    protected abstract suspend fun resultCall(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> = result
}