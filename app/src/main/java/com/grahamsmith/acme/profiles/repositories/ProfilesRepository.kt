package com.grahamsmith.acme.profiles.repositories

import com.grahamsmith.acme.profiles.networking.LoadProfilesResult
import com.grahamsmith.acme.profiles.networking.ProfilesService

class ProfilesRepository(private val profilesService: ProfilesService) {

    //we could also add some persistence here but lets see what network caching does for us.

    suspend fun loadProfiles(): LoadProfilesResult {
        return profilesService.fetchProfiles()
    }
}