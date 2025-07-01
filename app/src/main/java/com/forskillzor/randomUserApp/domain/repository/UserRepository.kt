package com.forskillzor.randomUserApp.domain.repository

import com.forskillzor.randomUserApp.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserList(): Flow<List<User>>
    suspend fun refreshUserList()
}