package io.indrian.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ParentPlatformResponse(
    @SerializedName("platform")
    val platformResponse: PlatformResponse = PlatformResponse()
)