package com.uc.pencatatanmou_uc_mobdev.Network;

import com.uc.pencatatanmou_uc_mobdev.Model.MouResponse;
import com.uc.pencatatanmou_uc_mobdev.Model.RealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIendPoint {

    @GET("discover/movie")
    Call<MouResponse> getMou(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<RealResponse> getReal(@Query("api_key") String apiKey);

//    @GET("genre/movie/list")
//    Call<GenreResponse> getGenre(@Query("api_key") String apiKey);
//
//    @GET("genre/tv/list")
//    Call<GenreResponse> getGenreTv(@Query("api_key") String apiKey);
//
//    @GET("discover/tv")
//    Call<TvShowResponse> getTvs(@Query("api_key") String apiKey);
}
