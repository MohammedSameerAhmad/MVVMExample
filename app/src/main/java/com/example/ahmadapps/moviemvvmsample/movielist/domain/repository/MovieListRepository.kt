package com.example.ahmadapps.moviemvvmsample.movielist.domain.repository

import com.example.ahmadapps.moviemvvmsample.movielist.domain.model.Movie
import com.example.ahmadapps.moviemvvmsample.movielist.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
Created by Mohammed Sameer Ahmad Android learning
 */
interface MovieListRepository {
    @GET("movie/{category}")
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>
    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}