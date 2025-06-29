package com.forskillzor.randomUserApp.data.repository

import com.forskillzor.randomUserApp.data.api.UserApi
import com.forskillzor.randomUserApp.data.mapper.toDomain
import com.forskillzor.randomUserApp.domain.models.User
import com.forskillzor.randomUserApp.domain.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
): UserRepository {
    override fun getUserList(): Flow<List<User>> {
        return flow {
            val userList = api.getUserList()
            val list = userList.results.map { userDto -> userDto.toDomain() }
            emit(list)
        }
    }
}