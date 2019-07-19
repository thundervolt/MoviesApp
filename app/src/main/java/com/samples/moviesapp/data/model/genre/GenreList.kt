package com.samples.moviesapp.data.model.genre

import com.google.gson.annotations.SerializedName

/**
 * Genre list
 *
 * @author AkashG
 * @since 18/07/19.
 */
data class GenreList(
    @SerializedName("genres") val genres: List<Genre>
)