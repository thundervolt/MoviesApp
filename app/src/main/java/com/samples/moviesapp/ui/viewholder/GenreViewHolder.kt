package com.samples.moviesapp.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samples.moviesapp.R
import com.samples.moviesapp.data.model.genre.Genre
import com.samples.moviesapp.ui.adapter.MoviesListAdapter
import kotlinx.android.synthetic.main.item_genre.view.*

/**
 * Genre ListItem ViewHolder
 *
 * @author AkashG
 * @since 18/07/19.
 */
class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    /**
     * bind data to genre view
     */
    fun bind(genre: Genre?, viewPool: RecyclerView.RecycledViewPool) {
        if (genre != null) {
            itemView.tv_genre_title.text = genre.name

            val moviesListAdapter = MoviesListAdapter(genre.moviesList)

            val childLayoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            childLayoutManager.initialPrefetchItemCount = 4
            childLayoutManager.isItemPrefetchEnabled = true

            itemView.rv_movie_list.apply {
                layoutManager = childLayoutManager
                adapter = moviesListAdapter
                setRecycledViewPool(viewPool)
                setItemViewCacheSize(5)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): GenreViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_genre, parent, false)
            return GenreViewHolder(view)
        }
    }
}