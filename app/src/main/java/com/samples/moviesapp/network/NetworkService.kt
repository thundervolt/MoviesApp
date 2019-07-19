package com.samples.moviesapp.network

import com.samples.moviesapp.data.model.genre.GenreList
import com.samples.moviesapp.data.model.movie.MoviesList
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit network service client
 *
 * @author AkashG
 * @since 18/07/19.
 */
interface NetworkService {

    @GET("movie/list?&api_key=f17e9c5e6c34ad9dc2bf6aab852c0cc7")
    fun getGenres(): Single<GenreList>

    @GET("{genreId}/movies?&api_key=f17e9c5e6c34ad9dc2bf6aab852c0cc7")
    fun getMovies(@Path("genreId") genreId: Int): Single<MoviesList>

    companion object {

        val BASE_URL = "https://api.themoviedb.org/3/genre/"
        val BASE_IMAGE_PATH_URL = "https://image.tmdb.org/t/p/w200/"

        fun getNetworkService(): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NetworkService::class.java)
        }
    }
}