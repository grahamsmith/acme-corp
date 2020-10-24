package com.grahamsmith.acme.models.networking.login

import com.grahamsmith.acme.models.networking.ApiResponseData

class LoginResponseData(

        override val userMessage: String?,
        val authToken: String?,
        val refreshToken: String?
) : ApiResponseData