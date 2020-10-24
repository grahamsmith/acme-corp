package com.grahamsmith.acme.networking.models.networking.profiles

import com.grahamsmith.acme.networking.models.networking.ApiResponseData

data class ProfilesResponseData(
        override val userMessage: String?,
        val profiles: List<Profile>
) : ApiResponseData
