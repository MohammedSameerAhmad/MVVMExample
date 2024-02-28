package com.example.ahmadapps.moviemvvmsample.movielist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahmadapps.moviemvvmsample.movielist.data.repository.MovieListRepositoryImpl
import com.example.ahmadapps.moviemvvmsample.movielist.domain.repository.MovieListRepository
import com.example.ahmadapps.moviemvvmsample.movielist.domain.util.Category
import com.example.ahmadapps.moviemvvmsample.movielist.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by Mohammed Sameer Ahmad Android learning
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepositoryImpl,
) : ViewModel() {
    private val _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getPopularMovieList(false)
        getUpcomingMovieList(false)
    }

    fun onEvent(event: MovieListUiEvent) {
//        when (event) {
//            MovieListUiEvent.Navigate -> {
//                _movieListState.update {
//                    it.copy(
//                        isCurrentPopularScreen = !_movieListState.value.isCurrentPopularScreen
//                    )
//                }
//            }
//
//            is MovieListUiEvent.Paginate -> {
//                if (event.category == Category.POPULAR) {
//                    getPopularMovieList(true)
//                } else if (event.category == Category.UPCOMING) {
//                    getUpcomingMovieList(true)
//                }
//            }
//        }
    }

    private fun getUpcomingMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Category.UPCOMING,
                movieListState.value.upcomingMovieListPage
            ).collectLatest { it ->
                when(it){
                    is Resource.Success -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                        it.data?.let {popularList ->
                            _movieListState.update {
                                it.copy(
                                    upcomingMovieList =  movieListState.value.upcomingMovieList
                                            + popularList.shuffled(),
                                    upcomingMovieListPage = movieListState.value.upcomingMovieListPage + 1

                                )

                            }
                        }
                    }
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = it.isLoading)
                        }
                    }
                }
            }
        }
    }

    private fun getPopularMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Category.POPULAR,
                movieListState.value.popularMovieListPage
            ).collectLatest { it ->
                when(it){
                    is Resource.Success -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                        it.data?.let {popularList ->
                            _movieListState.update {
                                it.copy(
                                    popularMovieList =  movieListState.value.popularMovieList
                                            + popularList.shuffled(),
                                    popularMovieListPage = movieListState.value.popularMovieListPage + 1

                                )

                            }
                        }
                    }
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = it.isLoading)
                        }
                    }
                }
            }
        }

    }
}