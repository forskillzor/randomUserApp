package com.forskillzor.randomUserApp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.forskillzor.randomUserApp.data.models.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}