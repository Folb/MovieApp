package com.computas.movieapp.repository;

import com.computas.movieapp.data.MovieList;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRemoteDataSource {
    private final String BASE_URL = "https://api.themoviedb.org/";

    private Retrofit client = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .build();

    public void getPopularMovies(final ResponseCallback<MovieList> callback) {
        Call call = client.create(MovieApiCalls.class).getPopularMovies(APIKey.API_KEY);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Something went wrong when fetching popular movies");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getTopRatedMovies(final ResponseCallback<MovieList> callback) {
        Call call = client.create(MovieApiCalls.class).getTopRatedMovies(APIKey.API_KEY);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Something went wrong when fetching top rated movies");
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
    }

    private HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}