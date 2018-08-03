package com.example.ahmedsaifaldeen.popularmoviesstage2.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmedsaifaldeen.popularmoviesstage2.R;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Trailers;

import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {
    Context context;
    int resources;
    List<Trailers> trailersList;


    public TrailersAdapter(Context context, int resources, List<Trailers> trailersList) {

        this.context = context;
        this.resources = resources;
        this.trailersList = trailersList;
    }

    @NonNull
    @Override
    public TrailersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trailres_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersAdapter.ViewHolder holder, int position) {

        final Trailers trailers =trailersList.get(holder.getAdapterPosition());
        holder.textView.setText(trailersList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" +trailers.getKey() ));
                context.startActivity(intent);

              //  Log.e("video",Uri.parse("http://www.youtube.com/watch?v=" + trailers.getKey()).toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return trailersList == null ? 0 : trailersList.size();
    }

    public List<Trailers> getTrailers() {
        return trailersList;
    }

    public void setTrailers(List<Trailers> trailersList) {
        this.trailersList.addAll(trailersList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.trailer_name);
        }
    }
}
