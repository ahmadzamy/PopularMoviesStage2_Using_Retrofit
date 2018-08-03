package com.example.ahmedsaifaldeen.popularmoviesstage2.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ahmedsaifaldeen.popularmoviesstage2.R;
import com.example.ahmedsaifaldeen.popularmoviesstage2.activities.DetailActivity;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Movies;

import java.util.List;

import static com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.MoviesContract.IMAGE_BASE_URL;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    Context context;
    List<Movies> moviesList;
    int resource;

    public MoviesAdapter(Context context, List<Movies> moviesList, int resource) {
        this.context = context;
        this.moviesList = moviesList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.movies_item
                        , parent
                        , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesAdapter.ViewHolder holder, int position) {
        holder.progressBar.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load(IMAGE_BASE_URL + moviesList.get(position).getPosterPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.image);

        holder.name.setText(moviesList.get(position).getOriginalTitle());

        final Movies movies = moviesList.get(holder.getAdapterPosition());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("MoviesData", movies);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    public List<Movies> getMoviesList() {
        return moviesList;
    }

    public void addMoviesList(List<Movies> moviesList) {
        this.moviesList.addAll(moviesList);
        notifyDataSetChanged();
    }

    public void clearList() {
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
