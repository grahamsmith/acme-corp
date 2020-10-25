package com.grahamsmith.acme.networking.models.networking.profiles

import com.grahamsmith.acme.networking.models.networking.ApiResponse
import com.grahamsmith.acme.networking.models.networking.ResponseMeta
import com.squareup.moshi.Json

data class ProfilesResponse(
    @field:Json(name = "meta") override val meta: ResponseMeta,
    @field:Json(name = "data") override val data: ProfilesResponseData
) : ApiResponse<ProfilesResponseData>