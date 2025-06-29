package com.example.forskillzor.data.mapper

import com.example.forskillzor.domain.models.Picture
import com.example.forskillzor.data.models.UserDto
import com.example.forskillzor.domain.models.Location
import com.example.forskillzor.domain.models.Street
import com.example.forskillzor.domain.models.User
import com.example.forskillzor.domain.models.UserName


fun UserDto.toDomain(): User {
    return User(
        name = UserName(
            title = this.name.title,
            first = this.name.first,
            last = this.name.last
        ),
        email = this.email,
        phone = this.phone,
        location = Location(
            street = Street(
                name = this.location.street.name,
                number = this.location.street.number
            ),
            city = this.location.city,
            country = this.location.country,
        ),
        picture = Picture(
            large = this.picture.large,
            medium = this.picture.medium,
            thumbnail = this.picture.thumbnail
        )
    )
}