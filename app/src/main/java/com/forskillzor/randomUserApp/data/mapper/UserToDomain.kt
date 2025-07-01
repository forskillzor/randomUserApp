package com.forskillzor.randomUserApp.data.mapper

import com.forskillzor.randomUserApp.domain.models.User

typealias UserEntity = com.forskillzor.randomUserApp.data.models.UserEntity

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        title = this.title,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        phone = this.phone,
        streetName = this.streetName,
        streetNumber = this.streetNumber,
        city = this.city,
        country = this.country,
        latitude = this.latitude,
        longitude = this.longitude,
        pictureLarge = this.pictureLarge,
        pictureMedium = this.pictureMedium
    )
}
