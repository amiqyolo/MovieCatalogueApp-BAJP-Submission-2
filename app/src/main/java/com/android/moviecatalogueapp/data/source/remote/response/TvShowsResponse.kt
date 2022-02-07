package com.android.moviecatalogueapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(

    @field:SerializedName("results")
    val results: List<TvShowsResultsItem>,
)
