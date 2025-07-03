package com.forskillzor.randomUserApp.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.forskillzor.randomUserApp.R
import com.forskillzor.randomUserApp.data.local.UserDao
import com.forskillzor.randomUserApp.data.models.UserEntity
import com.forskillzor.randomUserApp.data.repository.UserRepositoryImpl
import com.forskillzor.randomUserApp.di.AppModule
import com.forskillzor.randomUserApp.di.DatabaseModule
import com.forskillzor.randomUserApp.domain.models.User
import com.forskillzor.randomUserApp.ui.userList.UserListFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DatabaseModule::class, AppModule::class)
class UserListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject lateinit var userRepository: UserRepositoryImpl
    @Inject lateinit var userDao: UserDao
    @Inject lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        hiltRule.inject()
        runBlocking { userDao.deleteAll() }
    }

    @Test
    fun refreshUserList_andGetUserList_success() = runBlocking {
        val jsonResponse = """
            {
              "results": [
                {
                  "name": { "title": "Mr", "first": "Test", "last": "User" },
                  "email": "test@user.com",
                  "phone": "1234567890",
                  "location": {
                    "street": { "number": 1, "name": "Test Street" },
                    "city": "Test City",
                    "country": "Test Country",
                    "coordinates": { "latitude": "0.0", "longitude": "0.0" }
                  },
                  "picture": {
                    "large": "url_large",
                    "medium": "url_medium",
                    "thumbnail": "url_thumb"
                  }
                }
              ]
            }
        """.trimIndent()
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(jsonResponse)
        )

        userRepository.refreshUserList()

        val users: List<User> = userRepository.getUserList().first()
        assertEquals(1, users.size)
        val user = users[0]
        assertEquals("Test", user.firstName)
        assertEquals("User", user.lastName)
        assertEquals("test@user.com", user.email)
        assertEquals("1234567890", user.phone)
        assertEquals("Test Street", user.streetName)
        assertEquals(1, user.streetNumber)
        assertEquals("Test City", user.city)
        assertEquals("Test Country", user.country)
        assertEquals("0.0", user.latitude)
        assertEquals("0.0", user.longitude)
        assertEquals("url_large", user.pictureLarge)
        assertEquals("url_medium", user.pictureMedium)
    }
}
