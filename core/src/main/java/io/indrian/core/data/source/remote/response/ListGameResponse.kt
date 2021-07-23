package io.indrian.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ListGameResponse(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("results")
    val gameResponses: List<GameResponse> = listOf()
)