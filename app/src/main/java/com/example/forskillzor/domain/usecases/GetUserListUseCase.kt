package com.example.forskillzor.domain.usecases

import com.example.forskillzor.domain.models.User
import com.example.forskillzor.domain.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetUserListUseCase {

    operator fun invoke(): Flow<List<User>>

}

class GetUserListUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): GetUserListUseCase {
    override operator fun invoke(): Flow<List<User>> {
        return repository.getUserList()
    }
}