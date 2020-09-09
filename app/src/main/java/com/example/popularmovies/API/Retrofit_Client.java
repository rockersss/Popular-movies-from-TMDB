package com.example.popularmovies.API;

import com.example.popularmovies.Model.Service_Provider;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_Client {

    private static Retrofit retrofit = null;
    public static Retrofit getInstance(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                       .baseUrl(Service_Provider.BASE_URL)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();

        }

        return retrofit;

    }

}
