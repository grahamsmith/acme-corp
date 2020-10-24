package com.grahamsmith.acme.models.networking.profiles

import com.grahamsmith.acme.models.networking.ApiResponse
import com.grahamsmith.acme.models.networking.ResponseMeta

data class ProfilesResponse(override val meta: ResponseMeta, override val data: ProfilesResponseData) : ApiResponse<ProfilesResponseData>