package com.example.ahmedsaifaldeen.popularmoviesstage2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmedsaifaldeen.popularmoviesstage2.R;
import com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.Reviewers;

import java.util.List;

public class ReviewersAdapter extends RecyclerView.Adapter<ReviewersAdapter.ViewHolder> {
    Context context;
    int resources;
    List<Reviewers> reviewersList;


    public ReviewersAdapter(Context context, int resources, List<Reviewers> reviewersList) {
        this.context = context;
        this.resources = resources;

        this.reviewersList = reviewersList;
    }

    @NonNull
    @Override
    public ReviewersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reviewers_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewersAdapter.ViewHolder holder, int position) {

        holder.review.setText(reviewersList.get(position).getContent());
        holder.reviewersName.setText(reviewersList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return reviewersList == null ? 0 : reviewersList.size();
    }

    public List<Reviewers> getReviewersList() {
        return reviewersList;
    }

    public void setReviewersList(List<Reviewers> reviewersList) {
        this.reviewersList.addAll(reviewersList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView review;
        TextView reviewersName;

        public ViewHolder(View itemView) {
            super(itemView);
            review = itemView.findViewById(R.id.review);
            reviewersName = itemView.findViewById(R.id.reviewre_name);
        }
    }
}
