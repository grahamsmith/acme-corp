package com.grahamsmith.acme

import android.app.Application
import com.grahamsmith.acme.di.AppComponent
import com.grahamsmith.acme.di.AppModule
import com.grahamsmith.acme.di.DaggerAppComponent

class AcmeApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {

        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: AcmeApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}