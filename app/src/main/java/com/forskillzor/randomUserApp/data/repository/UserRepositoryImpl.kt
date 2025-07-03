package com.forskillzor.randomUserApp.data.repository

import com.forskillzor.randomUserApp.data.api.UserApi
import com.forskillzor.randomUserApp.data.local.UserDao
import com.forskillzor.randomUserApp.data.mapper.toDomain
import com.forskillzor.randomUserApp.data.mapper.toEntity
import com.forskillzor.randomUserApp.domain.models.User
import com.forskillzor.randomUserApp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.HttpException

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUserList(): Flow<List<User>> {
        return userDao.getAll().map { list ->
            list.map { it.toDomain() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun refreshUserList() {
        try {
            val newUsers = api.getUserList().results.map { it.toEntity() }
            userDao.deleteAll()
            userDao.insertAll(newUsers)
        } catch (e: IOException) {
            throw NetworkException("Network error: ${e.message}")
        } catch (e: HttpException) {
            throw NetworkException("HTTP error: ${e.message}")
        } catch (e: Exception) {
            throw RepositoryException("Repository Error: ${e.message}")
        }
    }
}

class NetworkException(message: String) : Exception(message)
class RepositoryException(message: String) : Exception(message)