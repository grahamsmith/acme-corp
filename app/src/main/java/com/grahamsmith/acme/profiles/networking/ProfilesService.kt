package com.grahamsmith.acme.profiles.networking

import com.grahamsmith.acme.authentication.AuthenticationManager
import com.grahamsmith.acme.networking.models.WebApi
import com.grahamsmith.acme.profiles.exceptions.ProfilesLoadFailureException
import com.grahamsmith.acme.profiles.networking.models.Profile

class ProfilesService(private val authenticationManager: AuthenticationManager, private val webApi: WebApi) {

    suspend fun fetchProfiles(): LoadProfilesResult {

        val fetchProfilesResult = webApi.getProfiles(authenticationManager.getUser().authToken)

        val userMessage = fetchProfilesResult.body()?.data?.userMessage.orEmpty()

        return if (!fetchProfilesResult.isSuccessful) {
            throw ProfilesLoadFailureException(userMessage)
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