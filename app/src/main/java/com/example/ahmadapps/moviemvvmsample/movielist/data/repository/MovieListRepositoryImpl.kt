package com.example.ahmadapps.moviemvvmsample.movielist.data.repository

import com.example.ahmadapps.moviemvvmsample.movielist.data.local.movie.MovieDatabase
import com.example.ahmadapps.moviemvvmsample.movielist.data.remote.MovieAPI
import com.example.ahmadapps.moviemvvmsample.movielist.data.remote.toMovie
import com.example.ahmadapps.moviemvvmsample.movielist.data.remote.toMovieEntity
import com.example.ahmadapps.moviemvvmsample.movielist.domain.model.Movie
import com.example.ahmadapps.moviemvvmsample.movielist.domain.repository.MovieListRepository
import com.example.ahmadapps.moviemvvmsample.movielist.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
Created by Mohammed Sameer Ahmad Android learning
 */
class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieAPI,
    private val movieDatabase: MovieDatabase
) : MovieListRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean, category: String, page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)
            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(data = localMovieList.map { movieEntity ->
                    movieEntity.toMovie(category)
                }))
                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMovieList(category, page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies..."))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies..."))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies..."))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }
            movieDatabase.movieDao.upsertMovieList(movieEntities)
            emit(Resource.Success(movieEntities.map {
                it.toMovie(category)
            }))
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading(true))
            val movieEntity = movieDatabase.movieDao.getMovieById(id)
            if (movieEntity != null) {
                emit(Resource.Success(movieEntity.toMovie(movieEntity.category)))
                emit(Resource.Loading(false))
                return@flow
            }
            emit(Resource.Error("Error no such movie..."))
            emit(Resource.Loading(false))
        }
    }
}