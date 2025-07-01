package com.forskillzor.randomUserApp.di

import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCase
import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCaseImpl
import com.forskillzor.randomUserApp.domain.usecases.RefreshUserListUseCase
import com.forskillzor.randomUserApp.domain.usecases.RefreshUserListUseCaseImpl
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

    @Binds
    @Singleton
    abstract fun bindRefreshUserListUseCase(
        impl: RefreshUserListUseCaseImpl
    ): RefreshUserListUseCase
}

