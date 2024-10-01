package com.example.endspointsretrofit.movies_api;


import retrofit2.Call;
import retrofit2.http.GET;

import com.example.endspointsretrofit.json_mapper.MovieResponse;

public interface MoviesAPI {
    // Routers!!! express.js
    @GET("movie/popular?api_key=d584ea8b0edde13d123d843ba4fc69e0")
    Call<MovieResponse> getPopularMovies();

    @GET("search/movie?api_key=d584ea8b0edde13d123d843ba4fc69e0")
    Call<MovieResponse> getBuscarMovies();

    @GET("movie/{movie_id}?api_key=d584ea8b0edde13d123d843ba4fc69e0")
    Call<MovieResponse> getDetallesMovies();

}

