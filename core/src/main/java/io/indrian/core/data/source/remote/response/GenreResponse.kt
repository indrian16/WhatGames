package io.indrian.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("games_count")
    val gamesCount: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image_background")
    val imageBackground: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("slug")
    val slug: String = ""
)