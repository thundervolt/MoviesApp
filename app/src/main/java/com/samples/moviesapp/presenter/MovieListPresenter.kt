package com.samples.moviesapp.presenter

import androidx.lifecycle.MutableLiveData
import com.samples.moviesapp.data.model.genre.Genre
import com.samples.moviesapp.data.model.movie.MoviesList
import com.samples.moviesapp.network.NetworkService
import com.samples.moviesapp.utils.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Movie list presenter
 *
 * @author AkashG
 * @since 18/07/19.
 */
class MovieListPresenter(val compositeDisposable: CompositeDisposable) {

    private val networkService = NetworkService.getNetworkService()
    val completableMovieList: MutableLiveData<MoviesList> = MutableLiveData()
    val completableGenreList: MutableLiveData<List<Genre>> = MutableLiveData()
    var state: MutableLiveData<State> = MutableLiveData()

    /**
     * get genre list api call
     */
    fun getGenreList() {
        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        completableGenreList.postValue(response.genres)
                    },
                    {
                        updateState(State.ERROR)
                    }
                )
        )
    }

    /**
     * get movie list api call
     */
    fun getMoviesList(genreId: Int) {
        compositeDisposable.add(
            networkService.getMovies(genreId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        completableMovieList.postValue(response)
                    },
                    {
                        // handle api error
                    }
                )
        )
    }

    /**
     * update ui state
     */
    fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun listIsEmpty(): Boolean {
        return completableGenreList.value?.isEmpty() ?: true
    }

}