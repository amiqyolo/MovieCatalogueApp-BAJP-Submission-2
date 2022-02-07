package com.android.moviecatalogueapp.ui.tvshows

import androidx.lifecycle.ViewModel
import com.android.moviecatalogueapp.data.MovieRepository

class TvShowsViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getTvShows() = repository.getTvShows()

}