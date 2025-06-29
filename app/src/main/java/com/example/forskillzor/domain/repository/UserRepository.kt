package com.example.forskillzor.domain.repository

import com.example.forskillzor.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserList(): Flow<List<User>>
}