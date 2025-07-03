package com.forskillzor.randomUserApp.data.repository

import com.forskillzor.randomUserApp.data.api.UserApi
import com.forskillzor.randomUserApp.data.local.UserDao
import com.forskillzor.randomUserApp.data.models.Coordinates
import com.forskillzor.randomUserApp.data.models.Location
import com.forskillzor.randomUserApp.data.models.Picture
import com.forskillzor.randomUserApp.data.models.Street
import com.forskillzor.randomUserApp.data.models.UserApiResult
import com.forskillzor.randomUserApp.data.models.UserDto
import com.forskillzor.randomUserApp.data.models.UserEntity
import com.forskillzor.randomUserApp.data.models.UserName
import com.forskillzor.randomUserApp.domain.models.User
import com.forskillzor.randomUserApp.domain.repository.UserRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import java.io.IOException
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertFailsWith

class UserRepositoryImplTest {
    private val api: UserApi = mockk()
    private val userDao: UserDao = mockk()
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(api, userDao)
    }

    @Test
    fun `getUserList should return flow from dao`() = runTest {

        // UserEntity from data layer and User from domain layer may not match in their fields

        val entity = UserEntity(
            id = 1,
            title = "Mr.",
            firstName = "John",
            lastName = "Doe",
            email = "john@doe.com",
            phone = "1234567890",
            streetNumber = 10,
            streetName = "Main St",
            city = "Metropolis",
            country = "Country",
            latitude = "0.0",
            longitude = "0.0",
            pictureLarge = "large.jpg",
            pictureMedium = "medium.jpg"
        )
        val expectedUser = User(
            id = 1,
            title = "Mr.",
            firstName = "John",
            lastName = "Doe",
            email = "john@doe.com",
            phone = "1234567890",
            streetNumber = 10,
            streetName = "Main St",
            city = "Metropolis",
            country = "Country",
            latitude = "0.0",
            longitude = "0.0",
            pictureLarge = "large.jpg",
            pictureMedium = "medium.jpg"
        )
        coEvery { userDao.getAll() } returns flowOf(listOf(entity))

        val flow = repository.getUserList()
        val result = flow.first()

        assertEquals(listOf(expectedUser), result)
    }

    @Test
    fun `refreshUserList save new users to database`() = runTest {
        val dto = UserDto(
            name = UserName("Mr.", "John", "Doe"),
            email = "john@doe.com",
            phone = "1234567890",
            location = Location(
                street = Street(10, "Main St"),
                city = "Metropolis",
                country = "Country",
                coordinates = Coordinates("0.0", "0.0")
            ),
            picture = Picture("large.jpg", "medium.jpg", "thumb.jpg")
        )
        val apiResult = UserApiResult(listOf(dto))

        coEvery { api.getUserList(any()) } returns apiResult
        coEvery { userDao.deleteAll() } just Runs
        coEvery { userDao.insertAll(any()) } just Runs

        repository.refreshUserList()

        coVerify(exactly = 1) { userDao.deleteAll() }
        coVerify(exactly = 1) {
            userDao.insertAll(match { users ->
                users.size == 1 && users.first().email == "john@doe.com"
            })
        }
    }

    @Test
    fun `refreshUserList throws NetworkException on IOException`() = runTest {
        coEvery { api.getUserList() } throws IOException("No internet")
        val exception = assertFailsWith<NetworkException> {
            repository.refreshUserList()
        }
        assertTrue(exception.message!!.contains("Network error"))
    }

    @Test
    fun `refreshUserList throws NetworkException on HttpException`() = runTest {
        val response = Response.error<Any>(404, ResponseBody.create(null, "Not found"))
        val httpException = HttpException(response)
        coEvery { api.getUserList(any()) } throws httpException
        val exception = assertFailsWith<NetworkException> {
            repository.refreshUserList()
        }
        assertTrue(exception.message!!.contains("HTTP error"))
    }

    @Test
    fun `refreshUserList throws RepositoryException on unknown error`() = runTest {
        coEvery { api.getUserList(any()) } throws IllegalStateException("Something went wrong")
        val exception = assertFailsWith<RepositoryException> {
            repository.refreshUserList()
        }
        assertTrue(exception.message!!.contains("Repository Error"))
    }
}