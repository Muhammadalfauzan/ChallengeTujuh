package com.example.challengeempat


import android.content.Context
import com.example.challengeempat.di.SingletonModule.provideDatabaseCart
import com.example.challengeempat.di.SingletonModule.provideRetrofitInstance
import org.junit.Assert.assertNotNull
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class SingletonModuleTest {

    @Mock
    private lateinit var context: Context

    @Test
    fun `test provideRetrofitInstance should create a non-null Retrofit instance`() {

        val okHttpClient = OkHttpClient.Builder().build()
        val gsonConverterFactory = GsonConverterFactory.create()
        val retrofit = provideRetrofitInstance(okHttpClient, gsonConverterFactory)

        assertNotNull(retrofit)
    }

    @Test
    fun `test provideCartDatabase should create a non-null CartDatabase instance`() {
        val cartDatabase = provideDatabaseCart(context)
        assertNotNull(cartDatabase)
    }

}