package com.computas.movieapp.repository;

import com.computas.movieapp.data.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MovieApiCalls {
    @GET("/3/movie/top_rated")
    Call<MovieList> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("/3/movie/popular")
    Call<MovieList> getPopularMovies(@Query("api_key") String apiKey);
}
