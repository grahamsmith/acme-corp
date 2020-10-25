package com.grahamsmith.acme.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.grahamsmith.acme.AcmeApplication
import com.grahamsmith.acme.R
import com.grahamsmith.acme.utils.Status
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
                when (resource.status) {
                    Status.SUCCESS -> finishWithSuccess()
                    Status.ERROR -> showErrorMessage(resource.message)
                    Status.LOADING -> showLoading()
                }
            }
        })
    }

    private fun showLoading() {
        fragment_login_login_button.visibility = View.INVISIBLE
        fragment_login_progress.visibility = View.VISIBLE
    }

    private fun showErrorMessage(errorMessage: String) {

        fragment_login_login_button.visibility = View.VISIBLE
        fragment_login_progress.visibility = View.INVISIBLE

        Snackbar.make(fragment_login_root, errorMessage, Snackbar.LENGTH_SHORT).apply {
            setTextColor(resources.getColor(R.color.white, null))
            setBackgroundTint(resources.getColor(R.color.red, null))

        }.show()

    }

    private fun finishWithSuccess() {
        callback.onLoginSuccess()
    }

    interface AuthenticationResultListener {
        fun onLoginSuccess()
    }
}

