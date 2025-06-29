package com.example.forskillzor.data.repository

import com.example.forskillzor.data.api.UserApi
import com.example.forskillzor.data.mapper.toDomain
import com.example.forskillzor.domain.models.User
import com.example.forskillzor.domain.repository.UserRepository
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