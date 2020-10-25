package com.grahamsmith.acme.ui.profiles

import com.grahamsmith.acme.authentication.AuthenticationManager
import com.grahamsmith.acme.networking.models.networking.Api
import com.grahamsmith.acme.networking.models.networking.profiles.Profile

class ProfilesService(private val authenticationManager: AuthenticationManager, private val api: Api) {

    suspend fun fetchProfiles(): LoadProfilesResult {

        val fetchProfilesResult = api.getProfiles(authenticationManager.getUser().authToken)

        val userMessage = fetchProfilesResult.body()?.data?.userMessage.orEmpty()

        return if (!fetchProfilesResult.isSuccessful) {
            LoadProfilesResult(userMessage = userMessage)
        } else {

            val profiles = fetchProfilesResult.body()?.data?.profiles ?: emptyList()

            LoadProfilesResult(isSuccessful = true,
                userMessage = userMessage,
                profiles = profiles.sortedByDescending { y -> y.starLevel })
        }
    }
}

data class LoadProfilesResult(
    val isSuccessful: Boolean = false,
    val userMessage: String = "",
    val profiles: List<Profile> = emptyList()
)