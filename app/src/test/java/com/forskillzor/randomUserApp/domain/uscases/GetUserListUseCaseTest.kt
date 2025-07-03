package com.forskillzor.randomUserApp.domain.uscases

import com.forskillzor.randomUserApp.domain.models.User
import com.forskillzor.randomUserApp.domain.repository.UserRepository
import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCase
import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCaseException
import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.flow.flowOf

class GetUserListUseCaseTest {
    private val repository: UserRepository = mockk()
    private lateinit var useCase: GetUserListUseCase

    @Before
    fun setup() {
        useCase = GetUserListUseCaseImpl(repository)
    }

    @Test
    fun `invoke should return users from repository`() = runTest {
        val testUser = User(
            id = 1,
            title = "Mr.",
            firstName = "John",
            lastName = "Weak",
            email = "johnweak@gmail.com",
            phone = "9454433232",
            streetNumber = 50,
            streetName = "1st avenue",
            city = "NewYork",
            country = "USA",
            latitude = "54.33444",
            longitude = "60.34344",
            pictureLarge = "",
            pictureMedium = ""
        )

        coEvery { repository.getUserList() } returns flowOf(listOf(testUser))

        val result = useCase()

        result.collect { users ->
            assertEquals(1, users.size)
            assertEquals("John", users[0].firstName)
        }
    }

    @Test(expected = GetUserListUseCaseException::class)
    fun `invoke should throw exception on repository failure`() = runTest {
        coEvery { repository.getUserList() } throws RuntimeException("DB error")
        useCase().collect{}
    }
}