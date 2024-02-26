package com.example.ahmadapps.moviemvvmsample.movielist.data.remote.responses
/**
Created by Mohammed Sameer Ahmad Android learning
 */
data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)