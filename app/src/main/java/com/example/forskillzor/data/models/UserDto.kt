package com.example.forskillzor.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserApiResult(
    val results: List<UserDto>
)

@Serializable
data class UserDto(
    val name: UserName,
    val email: String,
    val phone: String,
    val location: Location
)

@Serializable
data class UserName(
    val title: String,
    val first: String,
    val last: String
)

@Serializable
data class Location(
    val street: Street,
    val city: String,
    val country: String,
    val postcode: Int
)

@Serializable
data class Street(
    val number: Int,
    val name: String
)

@Serializable
data class Coordinates(
    val latitude: String,
    val longitude: String
)