package io.indrian.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("background_image")
    val backgroundImage: String = "",
    @SerializedName("genres")
    val genreResponses: List<GenreResponse> = listOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("updated")
    val updated: String = "",
)