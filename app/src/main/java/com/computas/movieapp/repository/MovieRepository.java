package com.computas.movieapp.repository;

import com.computas.movieapp.data.MovieList;

public class MovieRepository {

    MovieRemoteDataSource remoteDataSource;

    public MovieRepository(MovieRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public void fetchPopularMovies(ResponseCallback<MovieList> callback) {
        remoteDataSource.getPopularMovies(callback);
    }

    public void fetchTopRatedMovies(ResponseCallback<MovieList> callback) {
        remoteDataSource.getTopRatedMovies(callback);
    }
}
