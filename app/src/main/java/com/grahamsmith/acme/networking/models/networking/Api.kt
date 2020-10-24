package com.grahamsmith.acme.networking.models.networking

import com.grahamsmith.acme.networking.models.networking.login.LoginRequest
import com.grahamsmith.acme.networking.models.networking.login.LoginResponse
import com.grahamsmith.acme.networking.models.networking.profiles.ProfilesResponse
import retrofit2.http.*

interface Api {

    @POST("https://ho0lwtvpzh.execute-api.us-east-1.amazonaws.com/DummyLogin")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("https://ypznjlmial.execute-api.us-east-1.amazonaws.com/DummyProfileList")
    suspend fun getProfiles(@Header("authorization") authToken: String): ProfilesResponse
}
