package com.forskillzor.randomUserApp.ui.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: UserName,
    val email: String,
    val phone: String,
    val location: Location,
    val picture: Picture
): Parcelable

@Parcelize
data class UserName(
    val title: String,
    val first: String,
    val last: String
): Parcelable

@Parcelize
data class Location(
    val street: Street,
    val city: String,
    val country: String,
    val coordinates: Coordinates
): Parcelable

@Parcelize
data class Street(
    val number: Int,
    val name: String
): Parcelable

@Parcelize
data class Coordinates(
    val latitude: String,
    val longitude: String
): Parcelable

@Parcelize
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
): Parcelable
