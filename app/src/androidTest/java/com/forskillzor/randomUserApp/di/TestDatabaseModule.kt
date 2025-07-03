package com.forskillzor.randomUserApp.di

import android.content.Context
import androidx.room.Room
import com.forskillzor.randomUserApp.data.local.AppDatabase
import com.forskillzor.randomUserApp.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideInMemoryDb(@ApplicationContext context: Context): AppDatabase =
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
}