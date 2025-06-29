package com.example.forskillzor.data.api

import com.example.forskillzor.data.models.UserDto
import retrofit2.http.GET

interface UserApi {

    companion object {
        const val BASE_URL = "https://randomuser.me/api/"
    }

    @GET("/")
    suspend fun getUserList(): List<UserDto>
}