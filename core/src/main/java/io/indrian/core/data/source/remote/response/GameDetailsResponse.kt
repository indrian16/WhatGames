package io.indrian.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class GameDetailsResponse(
    @SerializedName("background_image")
    val backgroundImage: String = "",
    @SerializedName("description_raw")
    val descriptionRaw: String = "",
    @SerializedName("genres")
    val genres: List<GenreResponse> = listOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("updated")
    val updated: String = "",
    @SerializedName("website")
    val website: String = "",
)