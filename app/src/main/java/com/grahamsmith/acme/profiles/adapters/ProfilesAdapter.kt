package com.grahamsmith.acme.profiles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.grahamsmith.acme.R
import com.grahamsmith.acme.profiles.networking.models.Profile
import kotlinx.android.synthetic.main.view_holder_profile.view.*

class ProfilesAdapter(private val profiles: ArrayList<Profile>) :
    RecyclerView.Adapter<ProfilesAdapter.ProfileViewHolder>() {

    override fun getItemCount(): Int = profiles.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder =
        ProfileViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_profile, parent, false))

    fun addProfiles(profiles: List<Profile>) {

        this.profiles.apply {

            clear()
            addAll(profiles)
        }
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(profile: Profile) {

            itemView.apply {

                view_holder_profile_name_text.text = profile.name.orEmpty()
                view_holder_profile_distance_text.text = itemView.context.getString(R.string.miles_away, profile.distanceFromUser.orEmpty())
                view_holder_profile_rating.rating = profile.starLevel?.toFloat() ?: 0f //todo - need clear instructions on what to do here. We could validate more and skip the view but that seems harsh. Could hide the rating?
                view_holder_profile_rating_count_text.text = itemView.context.getString(R.string.number_of_ratings, profile.numberOfRatings)

                view_holder_profile_image.load(profile.profileImage) {

                    crossfade(true)
                    placeholder(R.drawable.ic_baseline_account_circle)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}
