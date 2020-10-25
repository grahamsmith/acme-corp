package com.grahamsmith.acme.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.grahamsmith.acme.R
import com.grahamsmith.acme.utils.getText
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModel: LoginFragmentViewModel

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
        //tell the main activity to move to list of profiles
    }
}

