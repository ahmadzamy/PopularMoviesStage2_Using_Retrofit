package com.example.ahmedsaifaldeen.popularmoviesstage2.activities;

import android.arch.lifecycle.Observer;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ahmedsaifaldeen.popularmoviesstage2.R;
import com.example.ahmedsaifaldeen.popularmoviesstage2.adapters.ReviewersAdapter;
import com.example.ahmedsaifaldeen.popularmoviesstage2.adapters.TrailersAdapter;
import com.example.ahmedsaifaldeen.popularmoviesstage2.database.AppExecutors;
import com.example.ahmedsaifaldeen.popularmoviesstage2.database.MoviesDataBase;
import com.example.ahmedsaifaldeen.popularmoviesstage2.database.MoviesEntry;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Movies;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.MoviesAdiGetData;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.MoviesSingleton;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Reviewers;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.ReviewersRoot;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Trailers;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.TrailersRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.MoviesContract.IMAGE_BASE_URL;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();

    Movies movies;
    MoviesAdiGetData reviewersApi;
    MoviesAdiGetData trailersApi;
    MoviesDataBase moviesDataBase;
    MoviesEntry entry;


    ImageView poster;
    TextView overView;
    TextView originalTitle;
    TextView votedAverage;
    TextView releasedDate;
    FloatingActionButton favouriteAB;

    RecyclerView trailerRecycler;
    RecyclerView reviewersRecycler;

    List<Trailers> trailersList = new ArrayList<>();
    List<Reviewers> reviewersList = new ArrayList<>();

    Call<TrailersRoot> call;
    Call<ReviewersRoot> reviewersCall;

    ReviewersAdapter reviewersAdapter;
    TrailersAdapter trailersAdapter;

    int favouriteId;
    boolean CHECKED = false;
    boolean isFavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        moviesDataBase = MoviesDataBase.getInstance(getApplicationContext());

        movies = new Movies();
        movies = getIntent().getParcelableExtra("MoviesData");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        setUpViews();
        listeners();

        trailersApi = MoviesSingleton.getRetrofit().create(MoviesAdiGetData.class);
        reviewersApi = MoviesSingleton.getRetrofit().create(MoviesAdiGetData.class);

        getSupportActionBar().setTitle(movies.getOriginalTitle());

        Glide.with(this)
                .load(IMAGE_BASE_URL + movies.getPosterPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(DetailActivity.this, "The image Can't be loaded", Toast.LENGTH_LONG).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(poster);

        call = trailersApi.getTrailers(String.valueOf(movies.getId()));
        call.enqueue(new TrailersClass());

        trailersAdapter = new TrailersAdapter(this, R.layout.trailres_item, trailersList);
        LinearLayoutManager layoutTrailers = new LinearLayoutManager(this);
        trailerRecycler.setLayoutManager(layoutTrailers);
        trailerRecycler.setAdapter(trailersAdapter);

        Log.e(TAG, call.request().url().toString());
        reviewersCall = reviewersApi.getRviewer(String.valueOf(movies.getId()));
        reviewersCall.enqueue(new ReviewersClass());

        reviewersAdapter = new ReviewersAdapter(this, R.layout.reviewers_item, reviewersList);
        LinearLayoutManager layoutReviewers = new LinearLayoutManager(this);
        reviewersRecycler.setHasFixedSize(false);
        reviewersRecycler.setLayoutManager(layoutReviewers);
        reviewersRecycler.setAdapter(reviewersAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkMoviesFavourite();

    }

    private void setUpViews() {
        favouriteAB = findViewById(R.id.fab);
        poster = findViewById(R.id.detail_image);
        overView = findViewById(R.id.detail_over_view);
        originalTitle = findViewById(R.id.detail_title);
        votedAverage = findViewById(R.id.detail_voted_avarege);
        releasedDate = findViewById(R.id.detail_released_date);
        trailerRecycler = findViewById(R.id.trailers);
        reviewersRecycler = findViewById(R.id.reviewers);

        overView.setText(movies.getOverview());
        originalTitle.setText(movies.getOriginalTitle());
        votedAverage.setText(String.valueOf(movies.getVoteAverage()));
        releasedDate.setText(movies.getReleaseDate());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                moviesDataBase.moviesDao().getMovieById(movies.getId())
                        .observe(DetailActivity.this, new Observer<MoviesEntry>() {
                            @Override
                            public void onChanged(@Nullable MoviesEntry moviesEntry) {
                                if (moviesEntry != null) {
                                    favouriteAB.setImageResource(R.drawable.ic_favorite_full);
                                    isFavourite = true;
                                    favouriteId = moviesEntry.getId();
                                }
                            }
                        });
            }
        });
    }

    private void listeners() {
        favouriteAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isFavourite) {
                    favouriteAB.setImageResource(R.drawable.ic_favorite_full);
                    addingAndDeletingMovie();
                } else {
                    favouriteAB.setImageResource(R.drawable.ic_favorite_blank);
                    addingAndDeletingMovie();
                }

            }
        });
    }

    private boolean isContains() {

        if (!CHECKED) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    if (moviesDataBase.moviesDao().getMovieById().contains(movies.getId())) {
                        CHECKED = true;
                    }
                }
            });

            return CHECKED;

        } else {
            return CHECKED = false;
        }
    }


    private void checkMoviesFavourite() {
        if (isContains()) {
            isFavourite = true;
            favouriteAB.setImageResource(R.drawable.ic_favorite_full);

        } else {
            isFavourite = false;
            favouriteAB.setImageResource(R.drawable.ic_favorite_blank);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
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


    private void addingAndDeletingMovie() {
        entry = new MoviesEntry
                (favouriteId,
                        movies.getPosterPath(),
                        movies.getTitle(),
                        movies.getVoteAverage(),
                        movies.getOverview(),
                        movies.getReleaseDate(),
                        movies.getId());


        if (!isFavourite) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    moviesDataBase.moviesDao().insertFavouriteMovie(entry);
                }
            });
            isFavourite = true;
        } else {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    moviesDataBase.moviesDao().deleteMovieFromFavourite(entry);

                }
            });
            isFavourite = false;
        }
    }

    private class TrailersClass implements Callback<TrailersRoot> {
        @Override
        public void onResponse(@NonNull Call<TrailersRoot> call, @NonNull Response<TrailersRoot> response) {
            TrailersRoot root = response.body();
            if (root != null) {
                if (root.getResults() != null) {
                    Log.e(getLocalClassName(), root.getResults().get(0).getName());

                    trailersAdapter.setTrailers(root.getResults());

                }
            }

        }

        @Override
        public void onFailure(@NonNull Call<TrailersRoot> call, @NonNull Throwable t) {
            Toast.makeText(DetailActivity.this, "You can not see any trailers or reviews while your offline", Toast.LENGTH_LONG).show();
        }
    }

    private class ReviewersClass implements Callback<ReviewersRoot> {

        @Override
        public void onResponse(@NonNull Call<ReviewersRoot> call, @NonNull Response<ReviewersRoot> response) {
            ReviewersRoot reviewersRoot = response.body();
            if (reviewersRoot != null) {
                if (reviewersRoot.getResults() != null) {
                    reviewersAdapter.setReviewersList(reviewersRoot.getResults());

                }
            }

        }

        @Override
        public void onFailure(@NonNull Call<ReviewersRoot> call, @NonNull Throwable t) {
            Toast.makeText(DetailActivity.this, "You can not see any trailers or reviews while you are offline", Toast.LENGTH_LONG).show();

        }
    }

}

