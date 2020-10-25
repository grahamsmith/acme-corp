package com.grahamsmith.acme.ui.profiles

import com.grahamsmith.acme.authentication.AuthenticationManager
import com.grahamsmith.acme.networking.models.networking.Api
import com.grahamsmith.acme.networking.models.networking.profiles.Profile

class ProfilesService(private val authenticationManager: AuthenticationManager, private val api: Api) {

    suspend fun fetchProfiles(): LoadProfilesResult {

        val fetchProfilesResult = api.getProfiles(authenticationManager.getUser().authToken)

        val userMessage = fetchProfilesResult.body()?.data?.userMessage.orEmpty()

        return if (!fetchProfilesResult.isSuccessful) {
            throw ProfilesLoadFailureException(message = userMessage)
        } else {

            val profiles = fetchProfilesResult.body()?.data?.profiles ?: emptyList()

            LoadProfilesResult(
                userMessage = userMessage,
                profiles = profiles.sortedByDescending { y -> y.numberOfRatings })
        }
    }
}

data class LoadProfilesResult(

    val userMessage: String = "",
    val profiles: List<Profile> = emptyList()
)