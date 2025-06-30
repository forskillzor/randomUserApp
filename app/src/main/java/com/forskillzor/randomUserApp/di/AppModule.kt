package com.forskillzor.randomUserApp.di

import com.forskillzor.randomUserApp.data.api.RetrofitClient
import com.forskillzor.randomUserApp.data.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideRandomUserApi(): UserApi {
        return RetrofitClient.api
    }
}