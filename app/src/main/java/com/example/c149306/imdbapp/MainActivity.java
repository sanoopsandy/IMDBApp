package com.example.c149306.imdbapp;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.c149306.imdbapp.databinding.ActivityMainBinding;
import com.example.c149306.imdbapp.network.IWebService;
import com.example.c149306.imdbapp.parsers.MovieListResponse;
import com.example.c149306.imdbapp.parsers.Result;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private IWebService networkCallService = IWebService.retrofit.create(IWebService.class);

    ActivityMainBinding binding;
    MyAdapter adapter;
    List<Result> results = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        int numberOfColumns = 3;
        binding.progress.setVisibility(View.VISIBLE);
        binding.rvMovies.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        fetchMovieList();
    }

    private void fetchMovieList() {

        Call<MovieListResponse> fetchMovieList= networkCallService.fetchMovieList(Constants.API_KEY, Constants.LANGUAGE, Constants.PAGE_NO);
        fetchMovieList.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                results = response.body().getResults();
                adapter = new MyAdapter(MainActivity.this, results);
                binding.rvMovies.setAdapter(adapter);
                binding.progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(view);
    }
}
