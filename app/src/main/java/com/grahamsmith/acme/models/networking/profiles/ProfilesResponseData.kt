package com.grahamsmith.acme.models.networking.profiles

import com.grahamsmith.acme.models.networking.ApiResponseData

data class ProfilesResponseData(
        override val userMessage: String?,
        val profiles: List<Profile>
) : ApiResponseData
