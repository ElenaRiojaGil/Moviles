package com.example.endspointsretrofit.retrofit;

import com.example.endspointsretrofit.movies_api.MoviesAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//https://api.themoviedb.org/3/movie/popular
public class RetrofitClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static MoviesAPI instance;

    public static MoviesAPI getInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(MoviesAPI.class);
        }

        return instance;
    }

}