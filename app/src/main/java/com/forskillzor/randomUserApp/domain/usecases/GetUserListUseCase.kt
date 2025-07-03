package com.forskillzor.randomUserApp.domain.usecases

import com.forskillzor.randomUserApp.domain.models.User
import com.forskillzor.randomUserApp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetUserListUseCase {

    suspend operator fun invoke(): Flow<List<User>>

}

class GetUserListUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetUserListUseCase {
    override suspend operator fun invoke(): Flow<List<User>> {
        return try {
            repository.getUserList()
        } catch (e: Exception) {
            throw GetUserListUseCaseException("some error in repository ${e.message}")
        }
    }
}

class GetUserListUseCaseException(message: String) : Exception(message)