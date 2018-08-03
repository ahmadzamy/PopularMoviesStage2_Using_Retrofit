package com.example.ahmedsaifaldeen.popularmoviesstage2.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmedsaifaldeen.popularmoviesstage2.viewModels.MainViewModel;
import com.example.ahmedsaifaldeen.popularmoviesstage2.R;
import com.example.ahmedsaifaldeen.popularmoviesstage2.adapters.MoviesAdapter;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.MoviesAdiGetData;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.MoviesSingleton;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Root;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.paginate.NoPaginate;

public class MainActivity extends AppCompatActivity {

    private static final int popularMovies = 1;
    private static final int topRatedMovies = 2;
    private static int moviesState = 1;
    RecyclerView recyclerView;
    MoviesAdapter adapter;
    MoviesAdiGetData getData;
    int page = 1;
    boolean isLoading = false;
    TextView noConnection;
    TextView seeOffline;
    ImageView noInternet;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        noConnection = findViewById(R.id.no_internet_text);
        seeOffline = findViewById(R.id.see_offline);
        noInternet = findViewById(R.id.no_internet);

        getData = MoviesSingleton.getRetrofit().create(MoviesAdiGetData.class);
        setUpRecyclerAndAdapter();


        NoPaginate paginate = NoPaginate.with(recyclerView).setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (!isLoading) {
                    isLoading = true;
                    page++;
                    retrieveMovies(moviesState);
                }
            }
        }).build();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavouriteActivity.class);
                startActivity(intent);

            }
        });
    }

    private void setUpRecyclerAndAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        adapter = new MoviesAdapter(this, viewModel.getMoviesList(), R.layout.movies_item);
        recyclerView.setAdapter(adapter);


    }

    private void retrieveMovies(int state) {
        switch (state) {
            case popularMovies:
                Log.i("state", String.valueOf(moviesState));

                getData.getPopular(page).enqueue(new MoviesRetrofit());
                break;
            case topRatedMovies:
                Log.i("state", String.valueOf(moviesState));

                getData.getTopRated(page).enqueue(new MoviesRetrofit());

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.top_rated:
                page = 1;
                moviesState = topRatedMovies;
                retrieveMovies(moviesState);
                adapter.clearList();
                viewModel.getMoviesList().clear();
                Log.i("top_rated", String.valueOf(topRatedMovies));
                break;

            case R.id.popular:
                page = 1;
                moviesState = popularMovies;
                retrieveMovies(moviesState);
                adapter.clearList();
                viewModel.getMoviesList().clear();
                Log.i("popular", String.valueOf(popularMovies));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MoviesRetrofit implements Callback<Root> {

        @Override
        public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
            Root root = response.body();
            if (root != null) {
                if (root.getResults() != null) {
                    Log.e(getLocalClassName(), root.getResults().get(0).getOriginalTitle());
                    adapter.addMoviesList(root.getResults());
                    viewModel.addMoviesList(root.getResults());
                    noConnection.setVisibility(View.GONE);
                    noInternet.setVisibility(View.GONE);
                    seeOffline.setVisibility(View.GONE);
                    Log.v(getLocalClassName(), response.raw().request().url().toString());
                }
            }
            isLoading = false;
        }

        @Override
        public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {
            isLoading = false;
            noConnection.setVisibility(View.VISIBLE);
            noInternet.setVisibility(View.VISIBLE);
            seeOffline.setVisibility(View.VISIBLE);

        }
    }
}
