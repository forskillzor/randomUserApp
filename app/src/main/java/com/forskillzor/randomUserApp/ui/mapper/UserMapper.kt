package com.forskillzor.randomUserApp.ui.mapper

import com.forskillzor.randomUserApp.ui.models.Coordinates
import com.forskillzor.randomUserApp.ui.models.Location
import com.forskillzor.randomUserApp.ui.models.Picture
import com.forskillzor.randomUserApp.ui.models.Street
import com.forskillzor.randomUserApp.ui.models.User
import com.forskillzor.randomUserApp.ui.models.UserName

typealias UserDomain = com.forskillzor.randomUserApp.domain.models.User

fun UserDomain.toUI(): User {
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
            coordinates = Coordinates(
                latitude = this.location.coordinates.latitude,
                longitude = this.location.coordinates.longitude
            )
        ),
        picture = Picture(
            large = this.picture.large,
            medium = this.picture.medium,
            thumbnail = this.picture.thumbnail
        )
    )
}
