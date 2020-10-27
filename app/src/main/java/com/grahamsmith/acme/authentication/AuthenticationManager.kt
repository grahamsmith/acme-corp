package com.grahamsmith.acme.authentication

import com.grahamsmith.acme.authentication.exceptions.LoginFailureException
import com.grahamsmith.acme.authentication.models.User
import com.grahamsmith.acme.authentication.networking.IAuthenticationService

class AuthenticationManager(
    private val authenticationStore: IAuthenticationStore,
    private val authenticationService: IAuthenticationService
) : IAuthenticationManager {

    override fun isUserLoggedIn() = authenticationStore.isUserLoggedIn()

    override fun getUser() = authenticationStore.getCurrentUser()

    override suspend fun login(username: String, password: String): Boolean {

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

interface IAuthenticationManager {

    fun isUserLoggedIn(): Boolean
    fun getUser(): User
    suspend fun login(username: String, password: String): Boolean
}

