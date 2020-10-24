package com.grahamsmith.acme.authentication

import com.grahamsmith.acme.authentication.models.User
import com.grahamsmith.acme.authentication.networking.AuthenticationService

class AuthenticationManager(private val authenticationStore: AuthenticationStore, private val authenticationService: AuthenticationService) {

    fun isUserLoggedIn() : Boolean {
        return authenticationStore.isUserLoggedIn()
    }

    fun getUser() : User {
        return authenticationStore.getCurrentUser()
    }

    suspend fun login(username: String, password: String) {
        return authenticationService.login(username, password)
    }

    fun storeUser(user: User) {
        authenticationStore.addUser(user)
    }
}