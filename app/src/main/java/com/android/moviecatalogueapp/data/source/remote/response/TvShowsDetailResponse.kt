package com.android.moviecatalogueapp.data.source.remote.response

import com.android.moviecatalogueapp.utils.Constants.IMAGE_URL
import com.google.gson.annotations.SerializedName

data class TvShowsDetailResponse(

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

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
