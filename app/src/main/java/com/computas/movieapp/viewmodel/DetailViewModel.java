package com.computas.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.computas.movieapp.data.Movie;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<String> _title = new MutableLiveData("");
    private MutableLiveData<String> _originalTitle = new MutableLiveData("");
    private MutableLiveData<String> _posterPath = new MutableLiveData("");
    private MutableLiveData<String> _synopsis = new MutableLiveData("");
    private MutableLiveData<String> _voteAverage = new MutableLiveData("");
    private MutableLiveData<String> _releaseDate = new MutableLiveData("");

    LiveData<String> title = _title;
    LiveData<String> originalTitle = _originalTitle;
    LiveData<String> posterPath = _posterPath;
    LiveData<String> synopsis = _synopsis;
    LiveData<String> voteAverage = _voteAverage;
    LiveData<String> releaseDate = _releaseDate;

    public void setData(Movie movie) {
        _title.setValue(movie.getTitle());
        _originalTitle.setValue(movie.getOriginalTitle());
        _posterPath.setValue(movie.getPosterPath());
        _synopsis.setValue(movie.getSynopsis());
        _voteAverage.setValue(String.valueOf(movie.getVoteAverage()));
        _releaseDate.setValue(movie.getReleaseDate());
    }

    public LiveData<String> getTitle() {
        return title;
    }

    public LiveData<String> getOriginalTitle() {
        return originalTitle;
    }

    public LiveData<String> getPosterPath() {
        return posterPath;
    }

    public LiveData<String> getSynopsis() {
        return synopsis;
    }

    public LiveData<String> getVoteAverage() {
        return voteAverage;
    }

    public LiveData<String> getReleaseDate() {
        return releaseDate;
    }
}
