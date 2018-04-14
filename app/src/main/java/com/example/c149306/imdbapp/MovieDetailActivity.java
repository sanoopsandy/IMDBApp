package com.example.c149306.imdbapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.example.c149306.imdbapp.databinding.MovieDetailActivityBinding;
import com.example.c149306.imdbapp.parsers.Result;

/**
 * Created by C149306 on 10/5/2017.
 */

public class MovieDetailActivity extends AppCompatActivity {

    MovieDetailActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.movie_detail_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getBundleExtra("m");
        Result movie = (Result) bundle.getSerializable("movie");

        if (movie != null) {
            String imgUrl = Constants.POSTER_BASE_URL + movie.getPosterPath();
            binding.setMovie(movie);
            binding.setImageUrl(imgUrl);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
