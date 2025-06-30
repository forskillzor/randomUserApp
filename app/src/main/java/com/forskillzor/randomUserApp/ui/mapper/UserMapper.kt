package com.forskillzor.randomUserApp.ui.mapper

import com.forskillzor.randomUserApp.ui.models.User

typealias UserDomain = com.forskillzor.randomUserApp.domain.models.User

fun UserDomain.toUI(): User {
    return User(
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
