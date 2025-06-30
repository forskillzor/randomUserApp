package com.forskillzor.randomUserApp.ui.userDetails

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.forskillzor.randomUserApp.R
import com.forskillzor.randomUserApp.databinding.FragmentUserDetailsBinding
import com.forskillzor.randomUserApp.ui.models.User
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private val args: UserDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentUserDetailsBinding
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = args.user
    }

    @SuppressLint("SetTextI18n", "QueryPermissionsNeeded")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(LayoutInflater.from(context) , container, false)
        with(binding) {
            fullName.text = "${user.name.first} ${user.name.last}"
            phone.text = "+ ${user.phone}"
            email.text = user.email
            country.text = user.location.country
            city.text = user.location.city
            street.text = "${user.location.street.name} ${user.location.street.number}"

            Glide.with(avatar.context)
                .load(user.picture.large)
                .centerCrop()
                .thumbnail(
                    Glide.with(avatar.context)
                        .load(user.picture.large)
                )
                .into(avatar)
            phone.setOnClickListener {
                startActivity(Intent(Intent.ACTION_DIAL, "tel:${user.phone}".toUri()))
            }
            email.setOnClickListener {
                startActivity(Intent(Intent.ACTION_SENDTO, "mailto:${user.email}".toUri()))
            }
            location.setOnClickListener {
                val latitude = user.location.coordinates.latitude
                val longitude = user.location.coordinates.longitude
                startActivity(Intent(Intent.ACTION_VIEW, "geo:$latitude,$longitude".toUri()))
            }

        }
        return binding.root
    }
}