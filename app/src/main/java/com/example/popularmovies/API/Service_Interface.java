package com.example.popularmovies.API;

import com.example.popularmovies.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service_Interface {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovie(@Query("api_key") String api_key);

}
