package com.computas.movieapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.computas.movieapp.data.MovieList;
import com.computas.movieapp.repository.MovieRemoteDataSource;
import com.computas.movieapp.repository.MovieRepository;
import com.computas.movieapp.repository.ResponseCallback;

public class MovieViewModel extends ViewModel {

    private final String TAG = this.getClass().getSimpleName();

    private MovieRepository repository = new MovieRepository(new MovieRemoteDataSource());
    private MutableLiveData<MovieList> _popularMovies = new MutableLiveData<>(new MovieList());
    private MutableLiveData<MovieList> _topRatedMovies = new MutableLiveData<>(new MovieList());

    LiveData<MovieList> popularMovies = _popularMovies;
    LiveData<MovieList> topRatedMovies = _topRatedMovies;

    public void init() {
        fetchPopularMovies();
        fetchTopRatedMovies();
    }

    public LiveData<MovieList> getPopularMovies() {
        return popularMovies;
    }

    public LiveData<MovieList> getTopRatedMovies() {
        return topRatedMovies;
    }

    public void fetchPopularMovies() {
        repository.fetchPopularMovies(new ResponseCallback<MovieList>() {
            @Override
            public void onSuccess(MovieList response) {
                _popularMovies.setValue(response);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e(TAG, errorMessage);
            }
        });
    }

    public void fetchTopRatedMovies() {
        repository.fetchTopRatedMovies(new ResponseCallback<MovieList>(){
            @Override
            public void onSuccess(MovieList response) {
                _topRatedMovies.setValue(response);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e(TAG, errorMessage);
            }
        });
    }
}

