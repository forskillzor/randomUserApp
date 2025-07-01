package com.forskillzor.randomUserApp.domain.usecases

import com.forskillzor.randomUserApp.domain.repository.UserRepository
import javax.inject.Inject

interface RefreshUserListUseCase {

    suspend operator fun invoke()

}

class RefreshUserListUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): RefreshUserListUseCase {
    override suspend operator fun invoke() {
        repository.refreshUserList()
    }
}