package com.samples.moviesapp.data.model.movie

import com.google.gson.annotations.SerializedName

/**
 * Movie list
 *
 * @author AkashG
 * @since 18/07/19.
 */
data class MoviesList(@SerializedName("id") val genreId: Int, @SerializedName("results") val movies: List<Movie>)