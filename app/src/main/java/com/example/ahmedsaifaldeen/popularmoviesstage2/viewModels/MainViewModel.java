package com.example.ahmedsaifaldeen.popularmoviesstage2.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Movies;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private List<Movies> moviesList = new ArrayList<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public List<Movies> getMoviesList() {
        return moviesList;
    }

    public void addMoviesList(List<Movies> moviesList) {
        this.moviesList.addAll(moviesList);
    }
}
