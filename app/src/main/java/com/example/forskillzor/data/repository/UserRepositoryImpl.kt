package com.example.forskillzor.data.repository

import com.example.forskillzor.data.api.RetrofitClient
import com.example.forskillzor.data.mapper.toDomain
import com.example.forskillzor.domain.models.User
import com.example.forskillzor.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl: UserRepository {
    override fun getUserList(): Flow<List<User>> {
        return flow {
            val userList = RetrofitClient.api.getUserList()
                .map { userDto -> userDto.toDomain() }
            emit(userList)
        }
    }
}