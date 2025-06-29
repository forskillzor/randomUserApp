package com.forskillzor.randomUserApp.data.api

import com.forskillzor.randomUserApp.data.models.UserApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    companion object {
        const val BASE_URL = "https://randomuser.me"
    }

    @GET("/api/")
    suspend fun getUserList(
        @Query("results") results: Int = 20,
    ): UserApiResult
}