package com.example.taskmanager.di

import android.content.Context
import androidx.room.Room
import com.example.taskmanager.BuildConfig
import com.example.taskmanager.data.local.AppSharedPreference
import com.example.taskmanager.data.local.db.DatabaseService
import com.example.taskmanager.data.remote.ApiHelper
import com.example.taskmanager.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * <h1>ApplicationModule</h1>
 * <p>
 *  Hilt Application Module Implementation
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun  providesSharedPreferences(@ApplicationContext  context: Context) : AppSharedPreference {
        return AppSharedPreference(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient{

        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpBuilder
                .addInterceptor(loggingInterceptor)
        }

        return okHttpBuilder.build()
    }



    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiService: ApiService): ApiHelper = ApiHelper(apiService)

    @Provides
    @Singleton
    fun provideDatabaseService(@ApplicationContext  context: Context): DatabaseService =
        Room.databaseBuilder(
            context, DatabaseService::class.java,
            "todosqli"
        ).build()



}