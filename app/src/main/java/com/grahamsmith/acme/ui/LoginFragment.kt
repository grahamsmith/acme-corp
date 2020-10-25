package com.grahamsmith.acme.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.grahamsmith.acme.AcmeApplication
import com.grahamsmith.acme.BuildConfig
import com.grahamsmith.acme.R
import com.grahamsmith.acme.utils.getText
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModel: LoginFragmentViewModel

    lateinit var callback: AuthenticationResultListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AcmeApplication).appComponent.inject(this)
        callback = context as AuthenticationResultListener
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_login_login_button.setOnClickListener {
            performLogin()
        }

        fragment_login_register_button.setOnClickListener {
            context?.let {
                Toast.makeText(
                    it,
                    "Register Clicked",
                    Toast.LENGTH_SHORT
                )
            }
        }
    }

    private fun performLogin() {
        viewModel.logUserIn(
            fragment_login_username_input.getText(),
            fragment_login_password_input.getText()
        ).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.isSuccessful) {
                    true -> {
                        finishWithSuccess(it.userMessage)
                    }
                    else -> {
                        showErrorMessage(it.userMessage)
                    }
                }
            }
        })
    }

    private fun showErrorMessage(userMessage: String) {
        TODO("Not yet implemented")
    }

    private fun finishWithSuccess(userMessage: String) {
        callback.onLoginSuccess()
    }

    interface AuthenticationResultListener {
        fun onLoginSuccess()
    }
}

