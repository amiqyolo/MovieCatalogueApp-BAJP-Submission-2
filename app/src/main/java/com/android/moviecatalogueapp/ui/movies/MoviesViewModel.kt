package com.android.moviecatalogueapp.ui.movies

import androidx.lifecycle.ViewModel
import com.android.moviecatalogueapp.data.MovieRepository

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getMovies() = repository.getMovies()

}