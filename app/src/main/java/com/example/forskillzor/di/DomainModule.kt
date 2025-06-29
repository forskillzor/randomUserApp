package com.example.forskillzor.di

import com.example.forskillzor.domain.usecases.GetUserListUseCase
import com.example.forskillzor.domain.usecases.GetUserListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindGetUserListUseCase(
        impl: GetUserListUseCaseImpl
    ): GetUserListUseCase
}

