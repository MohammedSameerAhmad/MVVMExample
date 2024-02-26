package com.example.ahmadapps.moviemvvmsample.movielist.domain.util

/**
Created by Mohammed Sameer Ahmad Android learning
 */
sealed class Screen(val rout: String) {
    object Home : Screen("main")
    object PopularMovieList : Screen("popularMovie")
    object UpcomingMovieList : Screen("upcomingMovie")
    object Details : Screen("details")
}