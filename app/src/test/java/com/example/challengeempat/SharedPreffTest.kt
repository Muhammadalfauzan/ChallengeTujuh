package com.example.challengeempat
import android.content.Context
import android.content.SharedPreferences
import com.example.challengeempat.sharedpref.SharedPreffUser
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SharedPreffTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences

    @Mock
    private lateinit var mockEditor: SharedPreferences.Editor

    private lateinit var sharedPreffUser: SharedPreffUser

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        `when`(mockContext.getSharedPreferences(eq("MyPrefs"), eq(Context.MODE_PRIVATE)))
            .thenReturn(mockSharedPreferences)

        `when`(mockSharedPreferences.edit()).thenReturn(mockEditor)

        sharedPreffUser = SharedPreffUser(mockContext)
    }

    @Test
    fun `test isLoggedIn when user is logged in`() {
        // Given
        `when`(mockSharedPreferences.getBoolean(eq("isLoggedIn"), anyBoolean())).thenReturn(true)

        // When
        val result = sharedPreffUser.isLoggedIn()

        // Then
        assertTrue(result)
    }

    @Test
    fun `test isLoggedIn when user is not logged in`() {
        // Given
        `when`(mockSharedPreferences.getBoolean(eq("isLoggedIn"), anyBoolean())).thenReturn(false)

        // When
        val result = sharedPreffUser.isLoggedIn()

        // Then
        assertFalse(result)
    }

    @Test
    fun `test setLoggedIn`() {
        // When
        sharedPreffUser.setLoggedIn(true)

        // Then
        verify(mockEditor).putBoolean(eq("isLoggedIn"), eq(true))
        verify(mockEditor).apply()
    }
}
