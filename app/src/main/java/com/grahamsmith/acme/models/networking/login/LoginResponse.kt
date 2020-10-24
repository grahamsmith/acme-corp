package com.grahamsmith.acme.models.networking.login

import com.grahamsmith.acme.models.networking.ApiResponse
import com.grahamsmith.acme.models.networking.ResponseMeta

data class LoginResponse(override val meta: ResponseMeta, override val data: LoginResponseData) : ApiResponse<LoginResponseData>