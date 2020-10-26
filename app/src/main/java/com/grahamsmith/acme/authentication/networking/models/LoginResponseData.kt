package com.grahamsmith.acme.authentication.networking.models

import com.grahamsmith.acme.networking.models.ApiResponseData
import com.squareup.moshi.Json

class LoginResponseData(

        @field:Json(name = "user_message") override val userMessage: String?,
        @field:Json(name = "auth_token") val authToken: String?,
        @field:Json(name = "refresh_token") val refreshToken: String?
) : ApiResponseData