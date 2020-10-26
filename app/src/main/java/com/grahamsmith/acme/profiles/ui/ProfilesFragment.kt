package com.grahamsmith.acme.profiles.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.grahamsmith.acme.AcmeApplication
import com.grahamsmith.acme.R
import com.grahamsmith.acme.profiles.adapters.ProfilesAdapter
import com.grahamsmith.acme.profiles.networking.models.Profile
import com.grahamsmith.acme.utils.Status
import kotlinx.android.synthetic.main.fragment_profiles.*
import javax.inject.Inject

class ProfilesFragment : Fragment(R.layout.fragment_profiles) {

    @Inject
    lateinit var viewModel: ProfilesFragmentViewModel

    private val adapter: ProfilesAdapter = ProfilesAdapter(arrayListOf())

    override fun onAttach(context: Context) {

        super.onAttach(context)
        (context.applicationContext as AcmeApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragment_profiles_error_message_button.setOnClickListener { loadProfiles() }
        fragment_profiles_recycler.adapter = adapter
        loadProfiles()
    }

    private fun loadProfiles() {

        viewModel.loadProfiles().observe(viewLifecycleOwner) {

            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> renderProfiles(resource.data?.profiles ?: emptyList())
                    Status.ERROR -> showErrorMessage(resource.message)
                    Status.LOADING -> showLoading()
                }
            }
        }
    }

    private fun showLoading() {

        fragment_profiles_error_group.visibility = View.INVISIBLE
        fragment_profiles_progress.visibility = View.VISIBLE
        fragment_profiles_recycler.visibility = View.INVISIBLE
    }

    private fun renderProfiles(profiles: List<Profile>) {

        adapter.apply {

            addProfiles(profiles)
            notifyDataSetChanged()
        }

        fragment_profiles_error_group.visibility = View.INVISIBLE
        fragment_profiles_progress.visibility = View.INVISIBLE
        fragment_profiles_recycler.visibility = View.VISIBLE
    }

    private fun showErrorMessage(userMessage: String) {

        fragment_profiles_error_group.visibility = View.VISIBLE
        fragment_profiles_progress.visibility = View.INVISIBLE
        fragment_profiles_recycler.visibility = View.INVISIBLE

        fragment_profiles_error_message_text.text = userMessage
    }
}