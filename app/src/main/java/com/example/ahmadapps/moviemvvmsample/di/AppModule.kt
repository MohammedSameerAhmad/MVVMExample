package com.example.ahmadapps.moviemvvmsample.di

import com.example.ahmadapps.moviemvvmsample.movielist.data.remote.MovieAPI
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
Created by Mohammed Sameer Ahmad Android learning
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val interceptor: Interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit: MovieAPI = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(MovieAPI.BASE_URL)
        .client(okHttpClient)
        .build()
        .create(MovieAPI::class.java)
}