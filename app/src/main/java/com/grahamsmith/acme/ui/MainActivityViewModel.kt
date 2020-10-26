package com.grahamsmith.acme.ui

import androidx.lifecycle.ViewModel
import com.grahamsmith.acme.authentication.IAuthenticationManager

class MainActivityViewModel(private val authenticationManager: IAuthenticationManager) : ViewModel() {

    fun isUserLoggedIn(): Boolean {
        return authenticationManager.isUserLoggedIn()
    }
}