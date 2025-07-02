package com.forskillzor.randomUserApp.ui.userDetails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.forskillzor.randomUserApp.databinding.FragmentUserDetailsBinding
import com.forskillzor.randomUserApp.ui.models.User
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri
import com.forskillzor.randomUserApp.R
import com.google.android.material.snackbar.Snackbar

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
        binding = FragmentUserDetailsBinding.inflate(LayoutInflater.from(context), container, false)
        with(binding) {
            fullName.text = "${user.firstName} ${user.lastName}"
            phone.text = "+ ${user.phone}"
            email.text = user.email
            country.text = user.country
            city.text = user.city
            street.text = "${user.streetName} ${user.streetNumber}"

            Glide.with(avatar.context)
                .load(user.pictureLarge)
                .centerCrop()
                .thumbnail(
                    Glide.with(avatar.context)
                        .load(user.pictureLarge)
                )
                .into(avatar)
            phone.setOnClickListener {
                try {
                    startActivity(Intent(Intent.ACTION_DIAL, "tel:${user.phone}".toUri()))
                } catch (e: Exception) {
                        Snackbar.make(
                            binding.root, getString(R.string.call_dialer_application_not_found),
                            Snackbar.LENGTH_LONG
                        ).show()
                }
            }
            email.setOnClickListener {
                try {
                    startActivity(Intent(Intent.ACTION_SENDTO, "mailto:${user.email}".toUri()))
                } catch (e: Exception) {
                    Snackbar.make(
                        binding.root, getString(R.string.email_application_not_found),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            location.setOnClickListener {
                val latitude = user.latitude
                val longitude = user.longitude
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, "geo:$latitude,$longitude".toUri()))
                } catch (e: Exception) {
                    Snackbar.make(
                        binding.root, getString(R.string.map_application_not_found),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }

        }
        return binding.root
    }
}