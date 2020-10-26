package com.grahamsmith.acme.profiles.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.grahamsmith.acme.profiles.exceptions.ProfilesLoadFailureException
import com.grahamsmith.acme.profiles.repositories.ProfilesRepository
import com.grahamsmith.acme.utils.Resource

class ProfilesFragmentViewModel(private val profilesRepository: ProfilesRepository, private val genericErrorMessage: String) : ViewModel() {

    fun loadProfiles() = liveData {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = profilesRepository.loadProfiles()))
        } catch (exception: Exception) {

            val userMessage: String =
                if (exception is ProfilesLoadFailureException) {
                    exception.userMessage
                } else {
                    "An error occurred check internet connectivity and try again"
                }

            emit(Resource.error(data = null, message = userMessage))
        }
    }
}

