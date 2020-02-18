package com.computas.movieapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Movie {

    @SerializedName("original_title")
    String originalTitle;

    @SerializedName("poster_path")
    String posterPath;

    @SerializedName("overview")
    String synopsis;

    @SerializedName("vote_average")
    double voteAverage;

    @SerializedName("title")
    String title;

    @SerializedName("release_date")
    String releaseDate;


    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
