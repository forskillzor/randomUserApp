package com.example.forskillzor.domain.models

data class User(
    val name: UserName,
    val email: String,
    val phone: String,
    val location: Location
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
    val postcode: Int
)

data class Street(
    val number: Int,
    val name: String
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)
