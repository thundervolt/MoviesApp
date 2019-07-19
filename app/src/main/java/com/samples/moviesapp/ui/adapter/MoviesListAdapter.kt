package com.samples.moviesapp.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samples.moviesapp.data.model.movie.Movie
import com.samples.moviesapp.ui.viewholder.MoviesViewHolder

/**
 * Movies Adapter
 *
 * @author AkashG
 * @since 18-07-2019.
 */
class MoviesListAdapter(var moviesList: List<Movie>?) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return moviesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    fun getItem(position: Int): Movie {
        return moviesList!!.get(position)
    }

}