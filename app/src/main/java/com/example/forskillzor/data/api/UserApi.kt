package com.example.forskillzor.data.api

import com.example.forskillzor.data.models.UserApiResult
import retrofit2.http.GET

interface UserApi {

    companion object {
        const val BASE_URL = "https://randomuser.me"
    }

    @GET("/api/")
    suspend fun getUserList(): UserApiResult
}