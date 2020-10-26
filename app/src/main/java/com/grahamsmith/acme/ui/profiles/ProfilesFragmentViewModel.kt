package com.grahamsmith.acme.ui.profiles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.grahamsmith.acme.authentication.exceptions.LoginFailureException
import com.grahamsmith.acme.utils.Resource

class ProfilesFragmentViewModel(private val profilesRepository: ProfilesRepository) : ViewModel() {

    fun loadProfiles() = liveData {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = profilesRepository.loadProfiles()))
        } catch (exception: Exception) {

            val userMessage: String =
                if (exception is ProfilesLoadFailureException) {
                    exception.message.orEmpty()
                } else {
                    "An error occurred check internet connectivity and try again"
                }

            emit(Resource.error(data = null, message = userMessage))
        }
    }
}

