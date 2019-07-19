package com.samples.moviesapp.ui.adapter

import android.util.SparseIntArray
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samples.moviesapp.data.model.genre.Genre
import com.samples.moviesapp.data.model.movie.MoviesList
import com.samples.moviesapp.presenter.MovieListPresenter
import com.samples.moviesapp.ui.viewholder.GenreViewHolder
import kotlinx.android.synthetic.main.item_genre.view.*

/**
 * Genre Adapter
 *
 * @author AkashG
 * @since 18/07/19.
 */
class GenreListAdapter(val movieListPresenter: MovieListPresenter) : RecyclerView.Adapter<GenreViewHolder>() {

    var genreList: List<Genre>? = null
    private val viewPool = RecyclerView.RecycledViewPool()
    private val positionList: SparseIntArray = SparseIntArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return genreList?.size ?: 0
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = getItem(position)
        holder.bind(genre, viewPool)
        val lastSeenFirstPosition = positionList.get(position)
        if (lastSeenFirstPosition >= 0) {
            val layoutManager: LinearLayoutManager = holder.itemView.rv_movie_list.layoutManager as LinearLayoutManager
            layoutManager.scrollToPositionWithOffset(lastSeenFirstPosition, 0)
        }
        if (genre.moviesList == null || genre.moviesList!!.isEmpty())
            movieListPresenter.getMoviesList(genre.id)

    }

    override fun onViewRecycled(holder: GenreViewHolder) {
        // Store position
        val position = holder.getAdapterPosition()
        val layoutManager: LinearLayoutManager = holder.itemView.rv_movie_list.layoutManager as LinearLayoutManager
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
        positionList.put(position, firstVisiblePosition)

        super.onViewRecycled(holder)
    }

    fun getItem(position: Int): Genre {
        return genreList!!.get(position)
    }

    /**
     * update genre list
     */
    fun updateGenreList(genreList: List<Genre>) {
        this.genreList = genreList
        notifyDataSetChanged()
    }

    /**
     * update genre movies list
     */
    fun updateMovieList(moviesList: MoviesList) {
        //  val oldGenreList: List<Genre>? = genreList
        for (genreIndex in 0..genreList!!.size) {
            val genre = genreList!!.get(genreIndex)
            if (genre.id == moviesList.genreId) {
                genre.moviesList = moviesList.movies
                notifyItemChanged(genreIndex)
                break
            }
        }
        /*val diffUtilCallback = DiffUtilCallback(oldGenreList!!, genreList!!)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        diffResult.dispatchUpdatesTo(this)*/
    }

}