package com.grahamsmith.acme.authentication.networking

import com.grahamsmith.acme.authentication.networking.models.LoginRequest
import com.grahamsmith.acme.networking.models.WebApi

class AuthenticationService(private val webApi: WebApi) {

    suspend fun login(username: String, password: String): LoginResult {

        val response = webApi.login(LoginRequest(username, password))

        return if (response.isSuccessful) {

            val data = response.body()?.data

            val authToken = data?.authToken.orEmpty()
            val refreshToken = data?.authToken.orEmpty()
            val userMessage = data?.userMessage.orEmpty()

            //todo - validate the shape of the refresh and auth tokens

            LoginResult(
                isSuccessful = true,
                authToken = authToken,
                refreshToken = refreshToken,
                userMessage = userMessage
            )
        } else {

            val userMessage = response.body()?.data?.userMessage ?: "Unknown error"
            LoginResult(userMessage = userMessage)
        }
    }
}

data class LoginResult(
    val isSuccessful: Boolean = false,
    val authToken: String = "",
    val refreshToken: String = "",
    val userMessage: String,
)