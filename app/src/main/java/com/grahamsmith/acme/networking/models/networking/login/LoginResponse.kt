package com.grahamsmith.acme.networking.models.networking.login

import com.grahamsmith.acme.networking.models.networking.ApiResponse
import com.grahamsmith.acme.networking.models.networking.ResponseMeta

data class LoginResponse(override val meta: ResponseMeta, override val data: LoginResponseData) : ApiResponse<LoginResponseData>