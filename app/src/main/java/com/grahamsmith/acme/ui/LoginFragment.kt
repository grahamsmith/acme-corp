package com.grahamsmith.acme.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.grahamsmith.acme.R
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModel: LoginFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_login_login_button.setOnClickListener { }
        fragment_login_register_button.setOnClickListener { context?.let { Toast.makeText(it, "Register Clicked", Toast.LENGTH_SHORT) } }
    }
}