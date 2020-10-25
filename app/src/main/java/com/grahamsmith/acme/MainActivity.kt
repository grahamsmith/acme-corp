package com.grahamsmith.acme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grahamsmith.acme.authentication.AuthenticationManager
import com.grahamsmith.acme.ui.LoginFragment
import com.grahamsmith.acme.ui.ProfilesFragment

class MainActivity : AppCompatActivity(), LoginFragment.AuthenticationResultListener {

    lateinit var authenticationManager: AuthenticationManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if(authenticationManager.isUserLoggedIn()) {
//            showProfileFragment()
//        } else {
//            showLoginFragment()
//        }

        showLoginFragment()
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