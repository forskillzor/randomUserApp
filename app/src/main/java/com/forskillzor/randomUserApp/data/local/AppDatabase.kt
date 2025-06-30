package com.forskillzor.randomUserApp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.forskillzor.randomUserApp.data.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}