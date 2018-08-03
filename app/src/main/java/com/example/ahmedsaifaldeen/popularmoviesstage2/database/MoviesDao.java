package com.example.ahmedsaifaldeen.popularmoviesstage2.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface MoviesDao {

    @Query("SELECT * FROM favourite_movies")
    LiveData<List<MoviesEntry>> getMovies();

    @Query("SELECT favID FROM favourite_movies")
    List<Integer> getMovieById();

    @Query("SELECT * FROM favourite_movies WHERE favID = :id")
    LiveData<MoviesEntry> getMovieById(int id);

    @Insert
    void insertFavouriteMovie(MoviesEntry moviesEntry);

    @Delete
    void deleteMovieFromFavourite(MoviesEntry moviesEntry);


}
