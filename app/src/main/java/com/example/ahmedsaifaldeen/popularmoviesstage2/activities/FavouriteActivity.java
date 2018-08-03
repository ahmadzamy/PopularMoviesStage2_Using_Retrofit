package com.example.ahmedsaifaldeen.popularmoviesstage2.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ahmedsaifaldeen.popularmoviesstage2.R;
import com.example.ahmedsaifaldeen.popularmoviesstage2.adapters.FavouriteAdapter;
import com.example.ahmedsaifaldeen.popularmoviesstage2.database.MoviesDataBase;
import com.example.ahmedsaifaldeen.popularmoviesstage2.database.MoviesEntry;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Movies;
import com.example.ahmedsaifaldeen.popularmoviesstage2.viewModels.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FavouriteActivity extends AppCompatActivity {

    TextView textView;
    RecyclerView recyclerView;

    MoviesDataBase moviesDataBase;
    List<MoviesEntry> entries;



    FavouriteAdapter favouriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Favourite");
        textView = findViewById(R.id.text);


        moviesDataBase = MoviesDataBase.getInstance(getApplicationContext());

        recyclerView = findViewById(R.id.favorite_list);
        entries = new ArrayList<>();


        favouriteAdapter = new FavouriteAdapter(this, R.layout.favourite_item, entries);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(favouriteAdapter);
        favoriteMovies();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("size1",String.valueOf(entries.size()));
    }

    private void favoriteMovies() {
        //final LiveData<List<MoviesEntry>> movieLiveData = moviesDataBase.moviesDao().getMovies();
        final MoviesViewModel viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        viewModel.getGetMoviesFromViewModel().observe(this, new Observer<List<MoviesEntry>>() {
            @Override
            public void onChanged(@Nullable final List<MoviesEntry> moviesEntries) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       favouriteAdapter.setMoviesList(moviesEntries);
                    }
                });

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);


    }
}
