package com.forskillzor.randomUserApp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
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
    val pictureMedium: String,
)