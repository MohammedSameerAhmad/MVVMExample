package com.example.ahmadapps.moviemvvmsample.di

import com.example.ahmadapps.moviemvvmsample.movielist.data.repository.MovieListRepositoryImpl
import com.example.ahmadapps.moviemvvmsample.movielist.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
Created by Mohammed Sameer Ahmad Android learning
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieListRepositoryImpl: MovieListRepositoryImpl
    ): MovieListRepository
}