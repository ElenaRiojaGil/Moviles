package com.example.endspointsretrofit.movies_api;


import retrofit2.Call;
import retrofit2.http.GET;

import com.example.endspointsretrofit.json_mapper.MovieResponse;

public interface MoviesAPI {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("search/movie")
    Call<MovieResponse> searchMovie(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String query, @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);
    // Routers!!! express.js

}

