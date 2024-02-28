package com.example.ahmadapps.moviemvvmsample.movielist.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

/**
Created by Mohammed Sameer Ahmad Android learning
 */
@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * from MovieEntity where id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * from MovieEntity where category = :category")
    suspend fun getMovieListByCategory(category: String): List<MovieEntity>
}