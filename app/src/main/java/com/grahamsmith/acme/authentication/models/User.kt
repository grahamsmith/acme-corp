package com.grahamsmith.acme.authentication.models

data class User(
    val email: String,
    val authToken: String,
    val refreshToken: String
)