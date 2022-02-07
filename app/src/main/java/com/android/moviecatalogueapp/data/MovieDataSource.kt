package com.android.moviecatalogueapp.data

import androidx.lifecycle.LiveData
import com.android.moviecatalogueapp.data.source.remote.response.MoviesDetailResponse
import com.android.moviecatalogueapp.data.source.remote.response.MoviesResultsItem
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsDetailResponse
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsResultsItem

interface MovieDataSource {

    fun getMovies(): LiveData<List<MoviesResultsItem>>

    fun getMoviesDetail(id: Int): LiveData<MoviesDetailResponse>

    fun getTvShows(): LiveData<List<TvShowsResultsItem>>

    fun getTvShowsDetail(id: Int): LiveData<TvShowsDetailResponse>
}