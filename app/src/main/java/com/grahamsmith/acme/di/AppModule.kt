package com.grahamsmith.acme.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.grahamsmith.acme.R
import com.grahamsmith.acme.authentication.AuthenticationManager
import com.grahamsmith.acme.authentication.AuthenticationStore
import com.grahamsmith.acme.authentication.EncryptedSharedPreferencesFactory
import com.grahamsmith.acme.authentication.networking.AuthenticationService
import com.grahamsmith.acme.networking.models.WebApi
import com.grahamsmith.acme.ui.MainActivityViewModel
import com.grahamsmith.acme.authentication.ui.LoginFragmentViewModel
import com.grahamsmith.acme.profiles.ui.ProfilesFragmentViewModel
import com.grahamsmith.acme.profiles.repositories.ProfilesRepository
import com.grahamsmith.acme.profiles.networking.ProfilesService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
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

    @Provides
    @Singleton
    @Named("CacheDirectory")
    fun provideCacheDirectory(): File = app.cacheDir

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
    fun provideAuthenticationService(webApi: WebApi) = AuthenticationService(webApi)

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
    fun provideApi(retrofit: Retrofit): WebApi {
        return retrofit.create(WebApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@Named("CacheDirectory") cacheDir: File): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(cacheDir, 10 * 1024 * 1024)) //10Mb
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
    fun provideProfilesService(authenticationManager: AuthenticationManager, webApi: WebApi) = ProfilesService(authenticationManager, webApi)

}