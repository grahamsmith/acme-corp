package com.grahamsmith.acme.authentication.networking.models

import com.grahamsmith.acme.networking.models.ApiResponse
import com.grahamsmith.acme.networking.models.ResponseMeta
import com.squareup.moshi.Json

data class LoginResponse(

    @field:Json(name = "meta") override val meta: ResponseMeta,
    @field:Json(name = "data") override val data: LoginResponseData
) : ApiResponse<LoginResponseData>