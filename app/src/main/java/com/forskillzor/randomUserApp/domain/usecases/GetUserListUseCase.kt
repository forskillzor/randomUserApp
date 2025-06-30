package com.forskillzor.randomUserApp.domain.usecases

import com.forskillzor.randomUserApp.domain.models.User
import com.forskillzor.randomUserApp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



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