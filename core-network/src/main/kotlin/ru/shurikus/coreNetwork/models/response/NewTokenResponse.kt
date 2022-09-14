package ru.shurikus.coreNetwork.models.response

import com.google.gson.annotations.SerializedName

internal data class NewTokenResponse(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean
)
