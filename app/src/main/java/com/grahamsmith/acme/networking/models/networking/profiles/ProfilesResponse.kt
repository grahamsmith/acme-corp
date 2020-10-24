package com.grahamsmith.acme.networking.models.networking.profiles

import com.grahamsmith.acme.networking.models.networking.ApiResponse
import com.grahamsmith.acme.networking.models.networking.ResponseMeta

data class ProfilesResponse(override val meta: ResponseMeta, override val data: ProfilesResponseData) : ApiResponse<ProfilesResponseData>