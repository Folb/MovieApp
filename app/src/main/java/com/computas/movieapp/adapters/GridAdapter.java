package com.computas.movieapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.computas.movieapp.R;
import com.computas.movieapp.data.Movie;
import com.computas.movieapp.data.MovieList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private MovieList data = new MovieList();
    private LayoutInflater inflater;

    public GridAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(MovieList data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.getMovies().size();
    }

    @Override
    public Object getItem(int i) {
        return data.getMovies().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView mImageView = new ImageView(context);

        String url = buildUri(data.getMovies().get(i));
        Glide.with(context).load(url).into(mImageView);

        return mImageView;
    }

    private String buildUri(Movie movie) {
        String baseUrl = "https://image.tmdb.org/t/p/";
        String imgSize = "w185";
        String posterPath = movie.getPosterPath();
        return baseUrl+imgSize+posterPath;
    }
}
