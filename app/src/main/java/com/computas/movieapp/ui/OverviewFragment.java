package com.computas.movieapp.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.computas.movieapp.ListType;
import com.computas.movieapp.adapters.GridAdapter;
import com.computas.movieapp.data.MovieList;
import com.computas.movieapp.viewmodel.MovieViewModel;
import com.computas.movieapp.R;

import java.util.List;

public class OverviewFragment extends Fragment {

    private MovieViewModel mViewModel;
    private GridAdapter gridAdapter;
    private ListType currentListType = ListType.Popular;

    public static OverviewFragment newInstance() {
        return new OverviewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridAdapter = new GridAdapter(getContext());
        GridView gridView = getActivity().findViewById(R.id.grid);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NavDirections action = OverviewFragmentDirections.actionOverviewFragmentToDetailsFragment(currentListType, i);
                Navigation.findNavController(view).navigate(action);
            }
        });

        mViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
        mViewModel.init();
        setViewModelObservers();
    }


    private void setViewModelObservers() {
        mViewModel.getPopularMovies().observe(getViewLifecycleOwner(), new Observer<MovieList>() {
            @Override
            public void onChanged(MovieList movieList) {
                if (currentListType == ListType.Popular) {
                    gridAdapter.setData(movieList);
                }
            }
        });

        mViewModel.getTopRatedMovies().observe(getViewLifecycleOwner(), new Observer<MovieList>() {
            @Override
            public void onChanged(MovieList movieList) {
                if (currentListType == ListType.TopRated) {
                    gridAdapter.setData(movieList);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popularMovies:
                gridAdapter.setData(mViewModel.getPopularMovies().getValue());
                currentListType = ListType.Popular;
                return true;
            case R.id.topRatedMovies:
                gridAdapter.setData(mViewModel.getTopRatedMovies().getValue());
                currentListType = ListType.TopRated;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
