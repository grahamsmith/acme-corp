package com.grahamsmith.acme.networking.models

import com.grahamsmith.acme.authentication.networking.models.LoginRequest
import com.grahamsmith.acme.authentication.networking.models.LoginResponse
import com.grahamsmith.acme.profiles.networking.models.ProfilesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface WebApi {

    @POST("DummyLogin")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("https://ypznjlmial.execute-api.us-east-1.amazonaws.com/DummyProfileList")
    suspend fun getProfiles(@Header("authorization") authToken: String): Response<ProfilesResponse>
}
