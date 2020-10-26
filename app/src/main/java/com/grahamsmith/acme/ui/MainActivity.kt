package com.grahamsmith.acme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grahamsmith.acme.AcmeApplication
import com.grahamsmith.acme.R
import com.grahamsmith.acme.authentication.AuthenticationManager
import com.grahamsmith.acme.ui.login.LoginFragment
import com.grahamsmith.acme.ui.profiles.ProfilesFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LoginFragment.AuthenticationResultListener {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as AcmeApplication).appComponent.inject(this)

        if(viewModel.isUserLoggedIn()) {
            showProfilesFragment()
        } else {
            showLoginFragment()
        }
    }

    override fun onLoginSuccess() {
        showProfilesFragment()
    }

    private fun showProfilesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_fragment_container, ProfilesFragment())
            .commit()
    }

    private fun showLoginFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_fragment_container, LoginFragment())
            .commit()
    }
}