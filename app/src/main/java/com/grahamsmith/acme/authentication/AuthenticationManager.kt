package com.grahamsmith.acme.authentication

import com.grahamsmith.acme.authentication.models.User
import com.grahamsmith.acme.authentication.networking.AuthenticationService
import com.grahamsmith.acme.authentication.networking.LoginResult

class AuthenticationManager(
    private val authenticationStore: AuthenticationStore,
    private val authenticationService: AuthenticationService
) {

    fun isUserLoggedIn() = authenticationStore.isUserLoggedIn()

    fun getUser() = authenticationStore.getCurrentUser()

    suspend fun login(username: String, password: String): LoginResult {

        val loginResult = authenticationService.login(username, password)

        if (loginResult.isSuccessful) {

            val user = User(username, loginResult.authToken, loginResult.refreshToken)
            storeUser(user)
        }

        return loginResult
    }

    private fun storeUser(user: User) = authenticationStore.addUser(user)
}