package com.forskillzor.randomUserApp.data.repository

import com.forskillzor.randomUserApp.data.api.UserApi
import com.forskillzor.randomUserApp.data.local.UserDao
import com.forskillzor.randomUserApp.data.mapper.toDomain
import com.forskillzor.randomUserApp.data.mapper.toEntity
import com.forskillzor.randomUserApp.data.models.UserDto
import com.forskillzor.randomUserApp.domain.models.User
import com.forskillzor.randomUserApp.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi,
    private val userDao: UserDao
) : UserRepository {
    private val repositoryScope = CoroutineScope(Dispatchers.IO)
    override fun getUserList(): Flow<List<User>> {
        return flow {
            try {
                val users = userDao.getAll()
                if (users.isEmpty()) {
                    val userList = fetchUserList().map { it.toEntity() }
                    userDao.insertAll(userList)
                }
                val list = userDao.getAll().map { it.toDomain() }
                emit(list)
            } catch (e: Exception) {
                throw RepositoryException("failed to get users ${e.message}")
            }
        }
    }

    override fun refreshUserList() {
//        val users = fetchUserList()
    }

    suspend fun fetchUserList(): List<UserDto> {
        try {
            return api.getUserList().results
        } catch (e: Exception) {
            throw NetworkException("Network error ${e.message}")
        }
    }
}

class NetworkException(message: String) : Exception(message)
class RepositoryException(message: String) : Exception(message)