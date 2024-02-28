package com.example.ahmadapps.moviemvvmsample.movielist.presentation

/**
Created by Mohammed Sameer Ahmad Android learning
 */
sealed class MovieListUiEvent {
    data class Paginate(val category: String) : MovieListUiEvent()
    object Navigate: MovieListUiEvent()
}