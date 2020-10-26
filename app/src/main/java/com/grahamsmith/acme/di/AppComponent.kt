package com.grahamsmith.acme.di

import com.grahamsmith.acme.authentication.ui.LoginFragment
import com.grahamsmith.acme.profiles.ui.ProfilesFragment
import com.grahamsmith.acme.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: LoginFragment)
    fun inject(target: MainActivity)
    fun inject(target: ProfilesFragment)
}