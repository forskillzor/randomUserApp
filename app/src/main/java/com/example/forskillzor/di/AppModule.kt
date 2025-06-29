package com.example.forskillzor.di

import com.example.forskillzor.data.api.RetrofitClient
import com.example.forskillzor.data.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideRandomUserApi(): UserApi {
        return RetrofitClient.api
    }
}