package com.grahamsmith.acme.ui.profiles

class ProfilesRepository(private val profilesService: ProfilesService) {

    //we could also add some persistence here but lets see what network caching does for us.

    suspend fun loadProfiles(): LoadProfilesResult {
        return profilesService.fetchProfiles()
    }
}