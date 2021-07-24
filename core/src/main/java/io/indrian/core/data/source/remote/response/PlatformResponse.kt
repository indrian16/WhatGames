package io.indrian.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class PlatformResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)