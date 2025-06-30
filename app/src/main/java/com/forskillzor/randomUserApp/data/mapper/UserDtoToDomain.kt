package com.forskillzor.randomUserApp.data.mapper

import com.forskillzor.randomUserApp.data.models.UserDto
import com.forskillzor.randomUserApp.domain.models.User

fun UserDto.toDomain(): User {
    return User(
        title = this.name.title,
        firstName = this.name.first,
        lastName = this.name.last,
        email = this.email,
        phone = this.phone,
        streetName = this.location.street.name,
        streetNumber = this.location.street.number,
        city = this.location.city,
        country = this.location.country,
        latitude = this.location.coordinates.latitude,
        longitude = this.location.coordinates.longitude,
        pictureLarge = this.picture.large,
        pictureMedium = this.picture.medium,
    )
}