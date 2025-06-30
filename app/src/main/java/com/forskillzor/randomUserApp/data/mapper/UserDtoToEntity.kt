package com.forskillzor.randomUserApp.data.mapper

import com.forskillzor.randomUserApp.data.models.UserDto
import com.forskillzor.randomUserApp.data.models.UserEntity

fun UserDto.toEntity(): UserEntity {
    return UserEntity(
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
