package com.grahamsmith.acme.authentication

import com.grahamsmith.acme.authentication.exceptions.LoginFailureException
import com.grahamsmith.acme.authentication.models.User
import com.grahamsmith.acme.authentication.networking.AuthenticationService

class AuthenticationManager(
    private val authenticationStore: AuthenticationStore,
    private val authenticationService: AuthenticationService
) {

    fun isUserLoggedIn() = authenticationStore.isUserLoggedIn()

    fun getUser() = authenticationStore.getCurrentUser()

    suspend fun login(username: String, password: String): Boolean {

        val loginResult = authenticationService.login(username, password)

        if (loginResult.isSuccessful) {

            val user = User(username, loginResult.authToken, loginResult.refreshToken)
            storeUser(user)
            return true
        } else {
            throw LoginFailureException(loginResult.userMessage)
        }
    }

    private fun storeUser(user: User) = authenticationStore.addUser(user)
}

