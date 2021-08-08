@file:Suppress("RemoveExplicitTypeArguments")

package io.indrian.core.data

import com.haroldadmin.cnradapter.NetworkResponse
import io.indrian.core.data.source.remote.response.ErrorResponse
import kotlinx.coroutines.flow.*
import timber.log.Timber

abstract class NetworkBoundResource<ResultType, RequestType : Any, ErrorType : Any> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is NetworkResponse.Success -> {
                    Timber.d("NetworkResponse.Success ${apiResponse.body}")

                    saveCallResult(apiResponse.body)
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
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
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(it)
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<NetworkResponse<RequestType, ErrorType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}