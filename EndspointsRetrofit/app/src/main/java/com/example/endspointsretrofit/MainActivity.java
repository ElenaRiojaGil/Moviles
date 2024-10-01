package com.example.endspointsretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.endspointsretrofit.json_mapper.Movie;
import com.example.endspointsretrofit.json_mapper.MovieResponse;
import com.example.endspointsretrofit.retrofit.RetrofitClient;

public class MainActivity extends AppCompatActivity {
    private Button btnBuscar;
    private Button btnDetalles;
    private Button btnObtener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<MovieResponse> call = RetrofitClient.getInstance().
                        getBuscarMovies();
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            List<Movie> movies = response.body().getResults();
                            // Procesa y muestra las películas aquí
                            for (Movie myMovie : movies
                            ) {
                                Toast.makeText(MainActivity.this,
                                        "Movie:" + myMovie.getTitle(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
            }
        });
        btnDetalles = findViewById(R.id.btnDetalles);
        btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<MovieResponse> call = RetrofitClient.getInstance().
                        getDetallesMovies();
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            List<Movie> movies = response.body().getResults();
                            // Procesa y muestra las películas aquí
                            for (Movie myMovie : movies
                            ) {
                                Toast.makeText(MainActivity.this,
                                        "Movie:" + myMovie.getTitle(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
            }
        });
        btnObtener = findViewById(R.id.btnObtener);
        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<MovieResponse> call = RetrofitClient.getInstance().
                        getPopularMovies();
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            List<Movie> movies = response.body().getResults();
                            // Procesa y muestra las películas aquí
                            for (Movie myMovie : movies
                            ) {
                                Toast.makeText(MainActivity.this,
                                        "Movie:" + myMovie.getTitle(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
