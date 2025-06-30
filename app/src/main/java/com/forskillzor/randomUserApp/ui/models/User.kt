package com.forskillzor.randomUserApp.ui.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val title: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val pictureLarge: String,
    val pictureMedium: String
): Parcelable