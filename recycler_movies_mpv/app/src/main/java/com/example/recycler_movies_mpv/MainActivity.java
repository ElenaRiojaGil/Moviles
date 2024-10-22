package com.example.recycler_movies_mpv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.recycler_movies_mpv.adapter.PeliculaAdapter;
import com.example.recycler_movies_mpv.data.MovieData;
import com.example.recycler_movies_mpv.json_mapper.Movie;
import com.example.recycler_movies_mpv.json_mapper.MovieResponse;
import com.example.recycler_movies_mpv.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        // initRetrofit();
    }

    private RecyclerView recyclerView;
    private PeliculaAdapter adapter;

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewPeliculas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //peliculas = cargarDatos();

        adapter = new PeliculaAdapter(this, MovieData.getMovieList());
        recyclerView.setAdapter(adapter);

    }

    private void initRetrofit() {
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
}