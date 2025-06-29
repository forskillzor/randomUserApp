package com.forskillzor.randomUserApp.domain.models

data class User(
    val name: UserName,
    val email: String,
    val phone: String,
    val location: Location,
    val picture: Picture
)

data class UserName(
    val title: String,
    val first: String,
    val last: String
)

data class Location(
    val street: Street,
    val city: String,
    val country: String,
)

data class Street(
    val number: Int,
    val name: String
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)
