package io.indrian.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: String = ""
)