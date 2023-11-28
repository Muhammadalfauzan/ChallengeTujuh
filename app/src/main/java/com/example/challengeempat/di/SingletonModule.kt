package com.example.challengeempat.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.challengeempat.api.ApiRestaurant
import com.example.challengeempat.database.DatabaseCart
import com.example.challengeempat.sharedpref.SharedPreffUser
import com.example.challengeempat.sharedpref.ViewPreference
import com.example.challengeempat.viewmodelregister.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {


    @Singleton
    @Provides
    fun provideDatabaseCart(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,DatabaseCart::class.java,"cart_database"
    ).build()

    @Singleton
    @Provides
    fun getDataDao(kelolaDB: DatabaseCart) = kelolaDB.cartDao()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiRestaurant {
        return retrofit.create(ApiRestaurant::class.java)
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://testing.jasa-nikah-siri-amanah-profesional.com/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    @Singleton
    @Provides
    fun providePreferencesManager(application: Application): ViewPreference {
        return ViewPreference(application)
    }

    @Singleton
    @Provides
    fun provideUserRepository(): UserRepository{
        return UserRepository()
    }
    @Singleton
    @Provides
    fun provideSharedPreffUser(application: Application): SharedPreffUser {
        return SharedPreffUser(application)
    }

}
