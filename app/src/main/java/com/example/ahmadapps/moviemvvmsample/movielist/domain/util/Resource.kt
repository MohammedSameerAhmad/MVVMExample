package com.example.ahmadapps.moviemvvmsample.movielist.domain.util

/**
Created by Mohammed Sameer Ahmad Android learning
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String?): Resource<T>(null, message)
    class Loading<T>(val isLoading: Boolean): Resource<T>(null)
}