package com.samples.moviesapp.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samples.moviesapp.R
import com.samples.moviesapp.data.model.movie.Movie
import com.samples.moviesapp.network.NetworkService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Movies ListItem ViewHolder
 *
 * @author AkashG
 * @since 18-07-2019.
 */
class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    /**
     * bind data to movie view
     */
    fun bind(movie: Movie?) {
        if (movie != null) {
            Picasso.get().load(NetworkService.BASE_IMAGE_PATH_URL + movie.imagePath).into(itemView.iv_movie_banner)
            itemView.tv_movie_title.text = movie.title
        }
    }

    companion object {
        fun create(parent: ViewGroup): MoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
            return MoviesViewHolder(view)
        }
    }
}