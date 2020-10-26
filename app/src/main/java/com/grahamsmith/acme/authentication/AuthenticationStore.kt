package com.grahamsmith.acme.authentication

import android.content.SharedPreferences
import com.grahamsmith.acme.authentication.models.User

class AuthenticationStore(private val sharedPreferences: SharedPreferences) : IAuthenticationStore {

    override fun addUser(user: User) {

        sharedPreferences
            .edit()
            .putString(EMAIL_ADDRESS, user.email)
            .putString(AUTH_TOKEN, user.authToken)
            .putString(REFRESH_TOKEN, user.refreshToken)
            .apply()
    }

    override fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getString(EMAIL_ADDRESS, null) != null
    }

    override fun getCurrentUser(): User {

        //Todo - we could use delegated properties
        //KAHN! - getString is annotated with nullable as null can be passed as the default value
        val emailAddress = sharedPreferences.getString(EMAIL_ADDRESS, "").orEmpty()
        val authToken = sharedPreferences.getString(AUTH_TOKEN, "").orEmpty()
        val refreshToken = sharedPreferences.getString(REFRESH_TOKEN, "").orEmpty()

        //TODO - we need to validate this but not enough time

        return User(emailAddress, authToken, refreshToken)
    }

    companion object {
        private const val EMAIL_ADDRESS = "EMAIL_ADDRESS"
        private const val AUTH_TOKEN = "AUTH_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }
}

interface IAuthenticationStore {

    fun addUser(user: User)
    fun isUserLoggedIn(): Boolean
    fun getCurrentUser(): User
}