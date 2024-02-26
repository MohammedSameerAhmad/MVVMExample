package com.example.ahmadapps.moviemvvmsample.movielist.data.remote

import com.example.ahmadapps.moviemvvmsample.movielist.data.remote.responses.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/**
Created by Mohammed Sameer Ahmad Android learning
 */
interface MovieAPI {
    @GET("movie/{category}")
    suspend fun getMovieList(
        @Path("category") category: String,
        @Query("page") pageNumber: Int,
        @Query("api_key") apiKey: String = API_KEY
    ) : MovieListDto
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "ae9b58a7c508e91fee147cb40067e20b"
    }
}