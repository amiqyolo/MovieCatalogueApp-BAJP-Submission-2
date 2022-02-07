package com.android.moviecatalogueapp.data.source.remote.response

import com.android.moviecatalogueapp.utils.Constants.IMAGE_URL
import com.google.gson.annotations.SerializedName

data class TvShowsResultsItem(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int>,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("id")
    val id: Int,
) {
    fun getPosterMovieImage(): String {
        return IMAGE_URL + posterPath
    }
}