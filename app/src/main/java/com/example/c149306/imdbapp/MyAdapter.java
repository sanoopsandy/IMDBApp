package com.example.c149306.imdbapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c149306.imdbapp.parsers.Result;

import java.util.ArrayList;
import java.util.List;
import com.example.c149306.imdbapp.BR;

/**
 * Created by C149306 on 10/5/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Result> movieList = new ArrayList<>();

    public
    MyAdapter(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.movie_card_tile, parent, false);
        return new ViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result movie = movieList.get(position);
        holder.bind(movie, position);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void openMovieDetails(Result movie){
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movie);
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("m", bundle);
        context.startActivity(intent);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding viewDataBinding;

        ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            viewDataBinding = itemView;
        }
        void bind(Result result, int i){
            String imageUrl = Constants.POSTER_BASE_URL+result.getPosterPath();
            viewDataBinding.setVariable(BR.movie, result);
            viewDataBinding.setVariable(BR.imageUrl, imageUrl);
            viewDataBinding.setVariable(BR.adapter, MyAdapter.this);
            viewDataBinding.setVariable(BR.index, i);
            viewDataBinding.executePendingBindings();
        }
    }
}
