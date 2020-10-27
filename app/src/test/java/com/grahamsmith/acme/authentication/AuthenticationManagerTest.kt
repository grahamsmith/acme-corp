package com.grahamsmith.acme.authentication

import com.grahamsmith.acme.authentication.exceptions.LoginFailureException
import com.grahamsmith.acme.authentication.models.User
import com.grahamsmith.acme.authentication.networking.IAuthenticationService
import com.grahamsmith.acme.authentication.networking.LoginResult
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AuthenticationManagerTest {

    @get:Rule
    val exceptionRule: ExpectedException = ExpectedException.none()

    @Mock
    private lateinit var mockAuthenticationService: IAuthenticationService

    @Mock
    private lateinit var mockAuthenticationStore: IAuthenticationStore

    private lateinit var authenticationManager: IAuthenticationManager

    @Before
    fun setUp() {
        authenticationManager = AuthenticationManager(
            mockAuthenticationStore,
            mockAuthenticationService
        )
    }

    @Test
    fun `Given a user is logged in When IsUserLoggedIn is invoked Then return value from Auth Store`() {

        whenever(mockAuthenticationStore.isUserLoggedIn()).thenReturn(true)

        val result = authenticationManager.isUserLoggedIn()

        verify(mockAuthenticationStore, times(1)).isUserLoggedIn()

        assertTrue(result)
    }

    @Test
    fun `Given a user is logged in When getUser is invoked Then return value from Auth Store`() {

        val user = User("email", "authToken", "refreshToken")

        whenever(mockAuthenticationStore.getCurrentUser()).thenReturn(user)

        val result = authenticationManager.getUser()

        verify(mockAuthenticationStore, times(1)).getCurrentUser()

        assertEquals(user, result)
    }

    @Test
    fun `Given a user has successfully authenticated When performing login Then the Auth Store stores the user`() = runBlocking {

        val expectedUsername = "username"
        val expectedPassword = "password"
        val expectedAuthToken = "authToken"
        val expectedRefreshToken = "refreshToken"

        val expectedUser = User(
            email = expectedUsername,
            authToken = expectedAuthToken,
            refreshToken = expectedRefreshToken
        )

        val loginResult = LoginResult(
            isSuccessful = true,
            authToken = expectedAuthToken,
            refreshToken = expectedRefreshToken,
            userMessage = ""
        )

        whenever(mockAuthenticationService.login(any(), any())).thenReturn(loginResult)

        val result = authenticationManager.login(expectedUsername, expectedPassword)

        verify(mockAuthenticationService, times(1)).login(expectedUsername, expectedPassword)
        verify(mockAuthenticationStore, times(1)).addUser(expectedUser)

        assertTrue(result)
    }

    @Test
    fun `Given a user did not authenticate When performing login Then an exception is thrown`() = runBlocking {

        val expectedUsername = "username"
        val expectedPassword = "password"
        val expectedMessage = "I am the expected message"

        val loginResult = LoginResult(
            isSuccessful = false,
            userMessage = expectedMessage
        )

        exceptionRule.expect(LoginFailureException::class.java)

        whenever(mockAuthenticationService.login(any(), any())).thenReturn(loginResult)

        authenticationManager.login(expectedUsername, expectedPassword)

        verify(mockAuthenticationService, times(1)).login(expectedUsername, expectedPassword)

        exceptionRule.expectMessage(expectedMessage)
    }
}