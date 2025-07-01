package com.forskillzor.randomUserApp.domain.usecases

import com.forskillzor.randomUserApp.domain.repository.UserRepository
import javax.inject.Inject

interface RefreshUserListUseCase {

    operator fun invoke()

}

class RefreshUserListUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): RefreshUserListUseCase {
    override operator fun invoke() {
        repository.refreshUserList()
    }
}