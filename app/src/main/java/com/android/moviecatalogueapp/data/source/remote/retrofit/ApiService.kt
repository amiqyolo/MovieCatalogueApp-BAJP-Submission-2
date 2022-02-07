package com.android.moviecatalogueapp.data.source.remote.retrofit

import com.android.moviecatalogueapp.data.source.remote.response.MoviesResponse
import com.android.moviecatalogueapp.data.source.remote.response.MoviesDetailResponse
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsDetailResponse
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/popular")
    fun getMovies(): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getMoviesDetail(@Path("movie_id") movieId: Int): Call<MoviesDetailResponse>

    @GET("tv/popular")
    fun getTvShows(): Call<TvShowsResponse>

    @GET("tv/{tv_id}")
    fun getTvShowsDetail(@Path("tv_id") tvId: Int): Call<TvShowsDetailResponse>
}