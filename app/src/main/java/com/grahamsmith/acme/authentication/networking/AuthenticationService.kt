package com.grahamsmith.acme.authentication.networking

import com.grahamsmith.acme.networking.models.networking.Api
import com.grahamsmith.acme.networking.models.networking.login.LoginRequest

class AuthenticationService(private val api: Api) {

    suspend fun login(username: String, password: String) {
        api.login(LoginRequest(username, password))
    }
}