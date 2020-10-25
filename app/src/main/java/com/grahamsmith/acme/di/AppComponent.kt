package com.grahamsmith.acme.di

import com.grahamsmith.acme.MainActivity
import com.grahamsmith.acme.ui.LoginFragment
import com.grahamsmith.acme.ui.ProfilesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: LoginFragment)
    fun inject(target: MainActivity)
    fun inject(target: ProfilesFragment)
}