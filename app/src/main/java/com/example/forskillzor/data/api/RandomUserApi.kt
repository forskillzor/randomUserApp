package com.example.forskillzor.data.api

import com.example.forskillzor.data.models.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomUserApi {

    companion object {
        const val BASE_URL = "https://randomuser.me/api/"
    }

    @GET("/")
    suspend fun getUserList(): List<UserDto>
}