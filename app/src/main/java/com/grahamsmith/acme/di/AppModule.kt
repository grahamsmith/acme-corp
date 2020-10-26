package com.grahamsmith.acme.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.grahamsmith.acme.R
import com.grahamsmith.acme.authentication.AuthenticationManager
import com.grahamsmith.acme.authentication.AuthenticationStore
import com.grahamsmith.acme.authentication.EncryptedSharedPreferencesFactory
import com.grahamsmith.acme.authentication.networking.AuthenticationService
import com.grahamsmith.acme.networking.models.Api
import com.grahamsmith.acme.ui.MainActivityViewModel
import com.grahamsmith.acme.ui.login.LoginFragmentViewModel
import com.grahamsmith.acme.ui.profiles.ProfilesFragmentViewModel
import com.grahamsmith.acme.ui.profiles.ProfilesRepository
import com.grahamsmith.acme.ui.profiles.ProfilesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    //todo - create smaller more precise modules e.g. android, authentication, networking, login etc

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    @Named("GenericErrorMessage")
    fun provideGenericErrorMessage(): String = app.resources.getString(R.string.generic_request_error_message)

    //authentication

    @Provides
    @Singleton
    fun provideSharedPreferencesFactory() = EncryptedSharedPreferencesFactory(app)

    @Provides
    @Singleton
    fun provideSharedPreferences(sharedPreferencesFactory: EncryptedSharedPreferencesFactory) = sharedPreferencesFactory.createSharedPreferences()

    @Provides
    @Singleton
    fun provideAuthenticationStore(sharedPreferences: SharedPreferences) = AuthenticationStore(sharedPreferences)

    @Provides
    @Singleton
    fun provideAuthenticationService(api: Api) = AuthenticationService(api)

    @Provides
    @Singleton
    fun provideAuthenticationManager(authenticationStore: AuthenticationStore, authenticationService: AuthenticationService) = AuthenticationManager(authenticationStore, authenticationService)

    // networking

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ho0lwtvpzh.execute-api.us-east-1.amazonaws.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .followRedirects(false)
            .build()
    }

    // main activity

    @Provides
    @Singleton
    fun provideMainActivityViewModel(authenticationManager: AuthenticationManager) = MainActivityViewModel(authenticationManager)

    // login

    @Provides
    @Singleton
    fun provideLoginFragmentViewModel(authenticationManager: AuthenticationManager, @Named("GenericErrorMessage") genericErrorMessageString: String) = LoginFragmentViewModel(authenticationManager, genericErrorMessageString)

    // profiles

    @Provides
    @Singleton
    fun provideProfilesFragmentViewModel(profilesRepository: ProfilesRepository, @Named("GenericErrorMessage") genericErrorMessageString: String) = ProfilesFragmentViewModel(profilesRepository, genericErrorMessageString)

    @Provides
    @Singleton
    fun provideProfilesRepository(profilesService: ProfilesService) = ProfilesRepository(profilesService)

    @Provides
    @Singleton
    fun provideProfilesService(authenticationManager: AuthenticationManager, api: Api) = ProfilesService(authenticationManager, api)

}