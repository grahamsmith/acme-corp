package com.grahamsmith.acme.networking.models.networking.login

import com.grahamsmith.acme.networking.models.networking.ApiResponse
import com.grahamsmith.acme.networking.models.networking.ResponseMeta
import com.squareup.moshi.Json

data class LoginResponse(
    @field:Json(name = "meta") override val meta: ResponseMeta,
    @field:Json(name = "data") override val data: LoginResponseData
) : ApiResponse<LoginResponseData>