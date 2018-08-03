package com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.MoviesContract.BASE_URL;

public class MoviesSingleton {

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            return retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else {
            return retrofit;
        }
    }
}
