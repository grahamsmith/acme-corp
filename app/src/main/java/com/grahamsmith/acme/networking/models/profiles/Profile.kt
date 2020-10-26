package com.grahamsmith.acme.networking.models.networking.profiles

import com.squareup.moshi.Json

data class Profile(
        @field:Json(name = "name") val name: String?,
        @field:Json(name = "star_level") val starLevel: Int?,
        @field:Json(name = "distance_from_user") val distanceFromUser: String?,
        @field:Json(name = "num_ratings") val numberOfRatings: Int?,
        @field:Json(name = "profile_image") val profileImage: String?
)