package com.example.ahmadapps.moviemvvmsample.movielist.domain.repository

import com.example.ahmadapps.moviemvvmsample.movielist.domain.model.Movie
import com.example.ahmadapps.moviemvvmsample.movielist.domain.util.Resource
import kotlinx.coroutines.flow.Flow

/**
Created by Mohammed Sameer Ahmad Android learning
 */
interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>
    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}