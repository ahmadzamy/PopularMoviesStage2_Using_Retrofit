package com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.ahmedsaifaldeen.popularmoviesstage2.networkUtils.MoviesContract.API_KEY;

public interface MoviesAdiGetData {

    @GET("popular?" + API_KEY)
    Call<Root> getPopular(@Query("page") int page);

    @GET("top_rated?" + API_KEY)
    Call<Root> getTopRated(@Query("page") int page);

    @GET("{id}/videos?" + API_KEY)
    Call<TrailersRoot> getTrailers(@Path("id") String id);

    @GET("{id}/reviews?" + API_KEY)
    Call<ReviewersRoot> getRviewer(@Path("id") String id);

    //https://api.themoviedb.org/3/movie/157336/reviews?api_key=20ce804c891dad55ad95cbf6159591a8
}
