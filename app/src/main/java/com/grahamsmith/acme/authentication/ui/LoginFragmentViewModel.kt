package com.grahamsmith.acme.authentication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.grahamsmith.acme.authentication.IAuthenticationManager
import com.grahamsmith.acme.authentication.exceptions.LoginFailureException
import com.grahamsmith.acme.utils.Resource

class LoginFragmentViewModel(private val authenticationManager: IAuthenticationManager, private val genericErrorMessage: String) :
    ViewModel() {

    fun logUserIn(username: String, password: String) = liveData {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = authenticationManager.login(username, password)))
        } catch (exception: Exception) {

            val userMessage: String =
                if (exception is LoginFailureException) {
                    exception.userMessage
                } else {
                    genericErrorMessage
                }

            emit(Resource.error(data = null, message = userMessage))
        }
    }
}