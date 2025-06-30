package com.forskillzor.randomUserApp.data.repository

import com.forskillzor.randomUserApp.data.api.UserApi
import com.forskillzor.randomUserApp.data.local.UserDao
import com.forskillzor.randomUserApp.data.mapper.toDomain
import com.forskillzor.randomUserApp.data.mapper.toEntity
import com.forskillzor.randomUserApp.domain.models.User
import com.forskillzor.randomUserApp.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi,
    private val userDao: UserDao
): UserRepository {
    override fun getUserList(): Flow<List<User>> {
        return flow {
            val users = userDao.getAll()
            if (users.isEmpty()) {
                val userList = api.getUserList().results.map {userDto -> userDto.toEntity()}
                userDao.insertAll(userList)
            }
            val list = userDao.getAll().map { user -> user.toDomain() }
            emit(list)
        }
    }
}