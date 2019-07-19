package com.samples.moviesapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.samples.moviesapp.data.model.genre.Genre

/**
 * DiffUtil to respond to genre list changes
 *
 * @author AkashG
 * @since 18-07-2019.
 */
class DiffUtilCallback(var oldMovieList: List<Genre>, var newMovieList: List<Genre>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldMovieList.size
    }

    override fun getNewListSize(): Int {
        return newMovieList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovieList.get(oldItemPosition).equals(newMovieList.get(newItemPosition))
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovieList.get(oldItemPosition) === newMovieList.get(newItemPosition)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }


}