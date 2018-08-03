package com.example.ahmedsaifaldeen.popularmoviesstage2.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.ahmedsaifaldeen.popularmoviesstage2.database.MoviesDataBase;
import com.example.ahmedsaifaldeen.popularmoviesstage2.database.MoviesEntry;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Movies;

import java.util.ArrayList;
import java.util.List;

public class MoviesViewModel extends AndroidViewModel {
    MoviesDataBase dataBase;

    private LiveData<List<MoviesEntry>> getMoviesFromViewModel;

    List<Movies> moviesList;


    public MoviesViewModel(@NonNull Application application) {
        super(application);

        dataBase = MoviesDataBase.getInstance(this.getApplication());
        moviesList = new ArrayList<>();
        getMoviesFromViewModel = dataBase.moviesDao().getMovies();
    }

    public LiveData<List<MoviesEntry>> getGetMoviesFromViewModel() {
        return getMoviesFromViewModel;
    }

    public List<Movies> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movies> moviesList) {
        this.moviesList = moviesList;
        notify();
    }
}
