package com.grahamsmith.acme.networking.models

import com.grahamsmith.acme.networking.models.login.LoginRequest
import com.grahamsmith.acme.networking.models.login.LoginResponse
import com.grahamsmith.acme.networking.models.profiles.ProfilesResponse
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @POST("DummyLogin")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("https://ypznjlmial.execute-api.us-east-1.amazonaws.com/DummyProfileList")
    suspend fun getProfiles(@Header("authorization") authToken: String): Response<ProfilesResponse>
}
