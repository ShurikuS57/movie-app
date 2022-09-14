package ru.shurikus.coreNetwork.models.request
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("request_token")
    var requestToken: String = "",
    @SerializedName("username")
    val username: String
)
