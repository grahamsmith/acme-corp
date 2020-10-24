package com.grahamsmith.acme.di

import com.grahamsmith.acme.ui.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: LoginFragment)
}