package com.forskillzor.randomUserApp.domain.usecases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.forskillzor.randomUserApp.data.local.UserDao
import com.forskillzor.randomUserApp.data.models.UserEntity
import com.forskillzor.randomUserApp.di.DatabaseModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
@RunWith(AndroidJUnit4::class)
class GetUserListUseCaseIntegrationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getUserListUseCase: GetUserListUseCase
    @Inject
    lateinit var userDao: UserDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun getUserList_returnsInsertedUsers() = runBlocking {
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
        userDao.insertAll(listOf(entity))

        val users = getUserListUseCase().first()
        assertEquals(1, users.size)
        assertEquals("John", users[0].firstName)
    }
}