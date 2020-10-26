package com.grahamsmith.acme.networking.models.networking.login

import com.grahamsmith.acme.networking.models.networking.ApiResponseData
import com.squareup.moshi.Json

class LoginResponseData(

        @field:Json(name = "user_message") override val userMessage: String?,
        @field:Json(name = "auth_token") val authToken: String?,
        @field:Json(name = "refresh_token") val refreshToken: String?
) : ApiResponseData