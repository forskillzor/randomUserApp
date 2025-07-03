package com.forskillzor.randomUserApp.ui.userList

import com.forskillzor.randomUserApp.domain.models.User as DomainUser
import com.forskillzor.randomUserApp.domain.usecases.GetUserListUseCase
import com.forskillzor.randomUserApp.domain.usecases.RefreshUserListUseCase
import com.forskillzor.randomUserApp.ui.mapper.toUI
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: UserListViewModel
    private val getUserListUseCase: GetUserListUseCase = mockk()
    private val refreshUserListUseCase: RefreshUserListUseCase = mockk()

    private val mockDomainUser =
        DomainUser(
            id = 1,
            title = "Ms.",
            firstName = "Alice",
            lastName = "Smith",
            email = "alice.smith@example.com",
            phone = "+1234567890",
            streetNumber = 42,
            streetName = "Baker Street",
            city = "London",
            country = "UK",
            latitude = "51.5074",
            longitude = "-0.1278",
            pictureLarge = "https://randomuser.me/api/portraits/women/1.jpg",
            pictureMedium = "https://randomuser.me/api/portraits/med/women/1.jpg"
        )

    private val mockUiUser = mockDomainUser.toUI()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun reset() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should call getUserList and emit Success`() = runTest(testDispatcher) {

        coEvery { getUserListUseCase() } returns flowOf(listOf(mockDomainUser))

        viewModel = UserListViewModel(getUserListUseCase, refreshUserListUseCase)

        assertEquals(UIState.Success(listOf(mockUiUser)), viewModel.state.value)
        coVerify(exactly = 1) { getUserListUseCase() }
    }

    @Test
    fun `refreshUserList should call refreshUserListUseCase`() = runTest(testDispatcher) {
        coEvery { refreshUserListUseCase() } returns Unit
        coEvery { getUserListUseCase() } returns flowOf(emptyList())
        viewModel = UserListViewModel(getUserListUseCase, refreshUserListUseCase)

        viewModel.refreshUserList()

        coVerify(exactly = 1) { refreshUserListUseCase() }
    }
    @Test
    fun `refreshUserList should emit Error when refreshUserListUseCase throws exception`() = runTest(testDispatcher) {
        val errorMessage = "Refresh failed"
        coEvery { refreshUserListUseCase() } throws RuntimeException(errorMessage)
        coEvery { getUserListUseCase() } returns flowOf(emptyList())
        viewModel = UserListViewModel(getUserListUseCase, refreshUserListUseCase)

        viewModel.refreshUserList()

        assertEquals(UIState.Error(errorMessage), viewModel.state.value)
        coVerify(exactly = 1) { refreshUserListUseCase() }
    }
}