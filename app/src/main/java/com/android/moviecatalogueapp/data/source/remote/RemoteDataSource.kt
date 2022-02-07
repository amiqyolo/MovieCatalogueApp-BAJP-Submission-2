package com.android.moviecatalogueapp.data.source.remote

import com.android.moviecatalogueapp.data.source.remote.response.*
import com.android.moviecatalogueapp.data.source.remote.retrofit.ApiService
import com.android.moviecatalogueapp.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val client: ApiService) {

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        client.getMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>,
            ) {
                val result = response.body()?.results
                if (response.isSuccessful && result != null) {
                    callback.onAllMoviesReceived(result)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailMovies(id: Int, callback: LoadDetailMoviesCallback) {
        EspressoIdlingResource.increment()
        client.getMoviesDetail(id).enqueue(object : Callback<MoviesDetailResponse> {
            override fun onResponse(
                call: Call<MoviesDetailResponse>,
                response: Response<MoviesDetailResponse>,
            ) {
                val result = response.body()
                if (response.isSuccessful && result != null) {
                    callback.onDetailMoviesReceived(result)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MoviesDetailResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        client.getTvShows().enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>,
            ) {
                val result = response.body()?.results
                if (response.isSuccessful && result != null) {
                    callback.onAllTvShowsReceived(result)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailTvShows(id: Int, callback: LoadDetailTvShowsCallback) {
        EspressoIdlingResource.increment()
        client.getTvShowsDetail(id).enqueue(object : Callback<TvShowsDetailResponse> {
            override fun onResponse(
                call: Call<TvShowsDetailResponse>,
                response: Response<TvShowsDetailResponse>,
            ) {
                val result = response.body()
                if (response.isSuccessful && result != null) {
                    callback.onDetailTvShowsReceived(result)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowsDetailResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesItem: List<MoviesResultsItem>)
    }

    interface LoadDetailMoviesCallback {
        fun onDetailMoviesReceived(detailMovies: MoviesDetailResponse)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowsItem: List<TvShowsResultsItem>)
    }

    interface LoadDetailTvShowsCallback {
        fun onDetailTvShowsReceived(detailTvShows: TvShowsDetailResponse)
    }

}