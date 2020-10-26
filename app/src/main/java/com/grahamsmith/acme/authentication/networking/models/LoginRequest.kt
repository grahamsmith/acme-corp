package com.grahamsmith.acme.authentication.networking.models

import com.squareup.moshi.Json

data class LoginRequest(
    @field:Json(name = "username") val username: String,
    @field:Json(name = "password") val password: String
)