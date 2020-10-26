package com.grahamsmith.acme.profiles.networking.models

import com.grahamsmith.acme.networking.models.ApiResponseData
import com.squareup.moshi.Json

data class ProfilesResponseData(

        @field:Json(name = "user_message") override val userMessage: String?,
        @field:Json(name = "profiles") val profiles: List<Profile>
) : ApiResponseData
