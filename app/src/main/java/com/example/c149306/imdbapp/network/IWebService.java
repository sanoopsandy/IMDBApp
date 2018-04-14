package com.example.c149306.imdbapp.network;

import com.example.c149306.imdbapp.Constants;
import com.example.c149306.imdbapp.parsers.MovieListResponse;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by C149306 on 10/4/2017.
 */

public interface IWebService {

    String fetchMovieList = "3/movie/now_playing";


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST(fetchMovieList)
    Call<MovieListResponse> fetchMovieList(@Query("api_key") String client_id,
                                           @Query("language") String token,
                                           @Query("page") String locate);
}
