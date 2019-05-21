package com.linkdev.demokotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TmdbMovieResponse {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("tmdbMovies")
    @Expose
    var tmdbMovies: List<TmdbMovie>? = null

}
