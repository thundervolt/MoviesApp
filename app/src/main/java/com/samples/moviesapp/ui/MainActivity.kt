package com.samples.moviesapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.samples.moviesapp.R
import com.samples.moviesapp.presenter.MovieListPresenter
import com.samples.moviesapp.ui.adapter.GenreListAdapter
import com.samples.moviesapp.utils.State
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var movieListPresenter: MovieListPresenter
    private lateinit var genreListAdapter: GenreListAdapter
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()
        movieListPresenter = MovieListPresenter(compositeDisposable)

        initAdapter()
        initState()
    }

    /**
     * Initialise recyclerView
     */
    private fun initAdapter() {
        movieListPresenter.getGenreList()

        genreListAdapter = GenreListAdapter(movieListPresenter)
        rv_genre_list.layoutManager = LinearLayoutManager(this)
        rv_genre_list.adapter = genreListAdapter
        rv_genre_list.setHasFixedSize(true)

        // observable to listen genre list changes
        movieListPresenter.completableGenreList.observe(this, Observer {
            rv_genre_list.visibility = View.VISIBLE
            genreListAdapter.updateGenreList(it)
        })

        // observable to listen movies list changes
        movieListPresenter.completableMovieList.observe(this, Observer {
            genreListAdapter.updateMovieList(it)
        })
    }

    /**
     * Initialise loading or error ui state
     */
    private fun initState() {
        movieListPresenter.state.observe(this, Observer { state ->
            progress_bar.visibility =
                if (movieListPresenter.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            tv_error.visibility =
                if (movieListPresenter.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            rv_genre_list.visibility =
                if (!movieListPresenter.listIsEmpty() && state == State.DONE) View.VISIBLE else View.GONE
        })
        // handle error click
        tv_error.setOnClickListener {
            movieListPresenter.getGenreList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
