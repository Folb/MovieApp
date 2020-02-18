package com.computas.movieapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavArgs;

import com.bumptech.glide.Glide;
import com.computas.movieapp.ListType;
import com.computas.movieapp.R;
import com.computas.movieapp.data.Movie;
import com.computas.movieapp.databinding.FragmentDetailsBinding;
import com.computas.movieapp.viewmodel.DetailViewModel;
import com.computas.movieapp.viewmodel.MovieViewModel;

public class DetailsFragment extends Fragment {

    private MovieViewModel movieViewModel;
    private DetailViewModel detailViewModel;
    private FragmentDetailsBinding binding;

    private ListType listType;
    private int listIndex;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);

        listType = DetailsFragmentArgs.fromBundle(getArguments()).getListType();
        listIndex = DetailsFragmentArgs.fromBundle(getArguments()).getListIndex();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
        detailViewModel = new ViewModelProvider(requireActivity()).get(DetailViewModel.class);

        Movie movie = null;
        if (listType == ListType.Popular) movie = movieViewModel.getPopularMovies().getValue().getMovies().get(listIndex);
        else if (listType == ListType.TopRated) movie = movieViewModel.getTopRatedMovies().getValue().getMovies().get(listIndex);

        if (movie != null) {
            detailViewModel.setData(movie);
            binding.setViewModel(detailViewModel);
            String url = buildUrl(movie);
            Glide.with(getContext()).load(url).into(binding.posterIv);
        }



    }

    private String buildUrl(Movie movie) {
        String baseUrl = "https://image.tmdb.org/t/p/";
        String imgSize = "w185";
        String posterPath = movie.getPosterPath();
        return baseUrl+imgSize+posterPath;
    }
}
