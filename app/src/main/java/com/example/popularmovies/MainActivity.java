package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.popularmovies.API.Retrofit_Client;
import com.example.popularmovies.API.Service_Interface;
import com.example.popularmovies.Adapter.MoviesAdapter;
import com.example.popularmovies.Model.Movie;
import com.example.popularmovies.Model.MovieResponse;
import com.example.popularmovies.Model.Service_Provider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MoviesAdapter adapter;
    private List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Service_Provider.API_KEY == null){

            Toast.makeText(this, "API key not found", Toast.LENGTH_SHORT).show();
            return;

        }

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Service_Interface service = Retrofit_Client.getInstance().create(Service_Interface.class);

        Call<MovieResponse> call = service.getPopularMovie(Service_Provider.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movies = response.body().getResults();
                adapter = new MoviesAdapter(movies, MainActivity.this);
                recyclerView.setAdapter(adapter);
                Toast.makeText(MainActivity.this, movies.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }
}