package com.android.moviecatalogueapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.moviecatalogueapp.data.source.remote.RemoteDataSource
import com.android.moviecatalogueapp.data.source.remote.response.MoviesDetailResponse
import com.android.moviecatalogueapp.data.source.remote.response.MoviesResultsItem
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsDetailResponse
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsResultsItem

class FakeMovieRepository(private val remote: RemoteDataSource) : MovieDataSource {

    override fun getMovies(): LiveData<List<MoviesResultsItem>> {
        val moviesList = MutableLiveData<List<MoviesResultsItem>>()

        remote.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesItem: List<MoviesResultsItem>) {
                moviesList.value = moviesItem
            }
        })

        return moviesList
    }

    override fun getMoviesDetail(id: Int): LiveData<MoviesDetailResponse> {
        val moviesDetail = MutableLiveData<MoviesDetailResponse>()

        remote.getDetailMovies(id, object : RemoteDataSource.LoadDetailMoviesCallback {
            override fun onDetailMoviesReceived(detailMovies: MoviesDetailResponse) {
                moviesDetail.value = detailMovies
            }
        })

        return moviesDetail
    }

    override fun getTvShows(): LiveData<List<TvShowsResultsItem>> {
        val tvShowsList = MutableLiveData<List<TvShowsResultsItem>>()

        remote.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowsItem: List<TvShowsResultsItem>) {
                tvShowsList.value = tvShowsItem
            }
        })

        return tvShowsList
    }

    override fun getTvShowsDetail(id: Int): LiveData<TvShowsDetailResponse> {
        val tvShowsDetail = MutableLiveData<TvShowsDetailResponse>()

        remote.getDetailTvShows(id, object : RemoteDataSource.LoadDetailTvShowsCallback {
            override fun onDetailTvShowsReceived(detailTvShows: TvShowsDetailResponse) {
                tvShowsDetail.value = detailTvShows
            }
        })

        return tvShowsDetail
    }
}