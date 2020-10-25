package com.grahamsmith.acme.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.grahamsmith.acme.authentication.AuthenticationManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragmentViewModel(private val authenticationManager: AuthenticationManager): ViewModel() {

    fun logUserIn(username: String, password: String) = liveData {

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { authenticationManager.login(username, password) }
            emit(result)
        }
    }
}