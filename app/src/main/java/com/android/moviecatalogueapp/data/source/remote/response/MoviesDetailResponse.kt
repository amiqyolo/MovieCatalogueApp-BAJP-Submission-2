package com.android.moviecatalogueapp.data.source.remote.response

import com.android.moviecatalogueapp.utils.Constants.IMAGE_URL
import com.google.gson.annotations.SerializedName

data class MoviesDetailResponse(

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,
) {
    fun getPosterMovieImage(): String {
        return IMAGE_URL + posterPath
    }

    fun getBackdropMovieImage(): String {
        return IMAGE_URL + backdropPath
    }
}
