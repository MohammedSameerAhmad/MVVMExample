package com.example.ahmadapps.moviemvvmsample.movielist.data.local.movie

import androidx.room.Database
import androidx.room.RoomDatabase

/**
Created by Mohammed Sameer Ahmad Android learning
 */
@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase: RoomDatabase() {

    abstract val movieDao: MovieDao
}