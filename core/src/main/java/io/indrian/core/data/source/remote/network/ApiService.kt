package io.indrian.core.data.source.remote.network

import io.indrian.core.data.source.remote.response.GameDetailsResponse
import io.indrian.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/games")
    suspend fun getGamesReleased(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 10,
        @Query("dates") updated: String = "2021-01-01,2021-12-31",
    ): ListGameResponse

    @GET("api/games")
    suspend fun getGamesRating(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 10
    ): ListGameResponse

    @GET("api/games")
    suspend fun searchGames(
        @Query("search") search: String,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 15,
    ): ListGameResponse

    @GET("api/games/{id}")
    suspend fun getGameDetails(@Path("id") id: Int): GameDetailsResponse?
}