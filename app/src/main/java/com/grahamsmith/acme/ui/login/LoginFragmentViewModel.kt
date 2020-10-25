package com.grahamsmith.acme.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.grahamsmith.acme.authentication.AuthenticationManager
import com.grahamsmith.acme.authentication.networking.LoginResult
import kotlinx.coroutines.*

class LoginFragmentViewModel(private val authenticationManager: AuthenticationManager): ViewModel() {

    fun logUserIn(username: String, password: String): LiveData<LoginResult> {
        return viewModelScope.async {
            authenticationManager.login(username, password)
        }.liveData()
    }
}

inline fun <reified T> Deferred<T>.liveData(): LiveData<T> {
    return liveData {
        emit(await())
    }
}