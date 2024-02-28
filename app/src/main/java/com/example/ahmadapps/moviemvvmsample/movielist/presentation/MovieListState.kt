package com.example.ahmadapps.moviemvvmsample.movielist.presentation

import com.example.ahmadapps.moviemvvmsample.movielist.domain.model.Movie

/**
Created by Mohammed Sameer Ahmad Android learning
 */
data class MovieListState(
    val isLoading: Boolean = false,
    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,
    val isCurrentPopularScreen: Boolean = true,
    val popularMovieList: List<Movie> = emptyList(),
    val upcomingMovieList: List<Movie> = emptyList(),
)