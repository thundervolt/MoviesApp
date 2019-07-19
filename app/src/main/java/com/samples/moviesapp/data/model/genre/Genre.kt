package com.samples.moviesapp.data.model.genre

import com.google.gson.annotations.SerializedName
import com.samples.moviesapp.data.model.movie.Movie

/**
 * Genre entity
 *
 * @author AkashG
 * @since 18/07/19.
 */
class Genre(@SerializedName("id") val id: Int, @SerializedName("name") val name: String, var moviesList: List<Movie>?)