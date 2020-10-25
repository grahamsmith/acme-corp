package com.grahamsmith.acme.ui.profiles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.grahamsmith.acme.utils.Resource

class ProfilesFragmentViewModel(private val profilesRepository: ProfilesRepository, private val profilesService: ProfilesService) : ViewModel() {

    fun loadProfiles() = liveData {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = profilesRepository.loadProfiles()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Unknown error"))
        }
    }
}

