package com.example.ahmedsaifaldeen.popularmoviesstage2.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ahmedsaifaldeen.popularmoviesstage2.R;
import com.example.ahmedsaifaldeen.popularmoviesstage2.activities.DetailActivity;
import com.example.ahmedsaifaldeen.popularmoviesstage2.database.MoviesEntry;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Movies;

import java.util.List;

import static com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.MoviesContract.IMAGE_BASE_URL;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    Context context;
    int recourse;
    List<MoviesEntry> moviesList;
    // List<Movies> favoriteMovies;
    Movies movies = new Movies();


    public FavouriteAdapter(Context context, int recourse, List<MoviesEntry> moviesList) {

        this.context = context;
        this.recourse = recourse;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favourite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavouriteAdapter.ViewHolder holder, final int position) {


        Log.e("LINK", IMAGE_BASE_URL + moviesList.get(position).getPosterPath());
        Glide.with(context)
                .load(IMAGE_BASE_URL + moviesList.get(position).getPosterPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(context, "Image Failed to Load", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.image);

        holder.name.setText(moviesList.get(position).getTitle());

        final MoviesEntry moviesEntry = moviesList.get(holder.getAdapterPosition());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movies.setOriginalTitle(moviesEntry.getTitle());
                movies.setPosterPath(moviesEntry.getPosterPath());
                movies.setOverview(moviesEntry.getOverview());
                movies.setId(moviesEntry.getFavID());
                movies.setReleaseDate(moviesEntry.getReleasedate());
                movies.setVoteAverage(moviesEntry.getVoteaverage());

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("MoviesData", movies);

                Log.e("size", String.valueOf(moviesList.size()));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    public List<MoviesEntry> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<MoviesEntry> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public void clearList(List<Movies> moviesList) {
        moviesList.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.poster_image);
            name = itemView.findViewById(R.id.movie_title);
            progressBar = itemView.findViewById(R.id.image_progress);
        }
    }
}
