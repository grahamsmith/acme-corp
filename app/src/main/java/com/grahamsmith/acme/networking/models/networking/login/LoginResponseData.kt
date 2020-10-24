package com.grahamsmith.acme.networking.models.networking.login

import com.grahamsmith.acme.networking.models.networking.ApiResponseData

class LoginResponseData(

        override val userMessage: String?,
        val authToken: String?,
        val refreshToken: String?
) : ApiResponseData