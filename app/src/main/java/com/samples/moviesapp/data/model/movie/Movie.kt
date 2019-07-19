package com.samples.moviesapp.data.model.movie

import com.google.gson.annotations.SerializedName

/**
 * Movie entity
 *
 * @author AkashG
 * @since 18/07/19.
 */
data class Movie(
    @SerializedName("title") val title: String, @SerializedName("poster_path") val imagePath: String
)