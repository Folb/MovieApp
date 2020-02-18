package com.computas.movieapp.data;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieList {

    @SerializedName("results")
    List<Movie> movies = new ArrayList<>();

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
