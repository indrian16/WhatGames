package io.indrian.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ListGenreResponse(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("results")
    val results: List<GenreResponse> = listOf()
)