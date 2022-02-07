package com.android.moviecatalogueapp.ui.detail

import androidx.lifecycle.ViewModel
import com.android.moviecatalogueapp.data.MovieRepository

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getMoviesDetail(moviesId: Int) = repository.getMoviesDetail(moviesId)

    fun getTvShowsDetail(tvShowsId: Int) = repository.getTvShowsDetail(tvShowsId)

}