package com.grahamsmith.acme.ui

import androidx.lifecycle.ViewModel
import com.grahamsmith.acme.authentication.AuthenticationManager

class MainActivityViewModel(private val authenticationManager: AuthenticationManager) : ViewModel() {

    fun isUserLoggedIn(): Boolean {
        return authenticationManager.isUserLoggedIn()
    }
}