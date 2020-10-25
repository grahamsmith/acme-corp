package com.grahamsmith.acme.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.grahamsmith.acme.authentication.AuthenticationManager
import com.grahamsmith.acme.authentication.exceptions.LoginFailureException
import com.grahamsmith.acme.utils.Resource

class LoginFragmentViewModel(private val authenticationManager: AuthenticationManager) :
    ViewModel() {

    fun logUserIn(username: String, password: String) = liveData {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = authenticationManager.login(username, password)))
        } catch (exception: Exception) {

            val userMessage: String =
                if (exception is LoginFailureException) {
                    exception.message.orEmpty()
                } else {
                    "An error occurred check internet connectivity and try again"
                }

            emit(Resource.error(data = null, message = userMessage))
        }
    }
}