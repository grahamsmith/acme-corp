package com.grahamsmith.acme.authentication

import com.grahamsmith.acme.authentication.models.User

class AuthenticationManager(private val authenticationStore: AuthenticationStore) {

    fun isUserLoggedIn() : Boolean {
        return authenticationStore.isUserLoggedIn()
    }

    fun getUser() : User {
        return authenticationStore.getCurrentUser()
    }

    fun logUserIn(user: User) {
        authenticationStore.addUser(user)
    }
}