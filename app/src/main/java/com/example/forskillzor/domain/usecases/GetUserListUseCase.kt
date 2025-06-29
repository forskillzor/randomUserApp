package com.example.forskillzor.domain.usecases

import com.example.forskillzor.data.repository.UserRepositoryImpl
import com.example.forskillzor.domain.models.User
import com.example.forskillzor.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

interface GetUserListUseCase {

    operator fun invoke(): Flow<List<User>>

}

class GetUserListUseCaseImpl: GetUserListUseCase {
    val repository: UserRepository = UserRepositoryImpl()
    override operator fun invoke(): Flow<List<User>> {
        return repository.getUserList()
    }
}