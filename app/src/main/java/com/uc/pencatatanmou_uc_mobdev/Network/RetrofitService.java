package com.uc.pencatatanmou_uc_mobdev.Network;

import com.uc.pencatatanmou_uc_mobdev.Model.MouResponse;
import com.uc.pencatatanmou_uc_mobdev.Model.RealResponse;
import com.uc.pencatatanmou_uc_mobdev.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private APIendPoint api;
    private static RetrofitService service;

    private RetrofitService() {
        api = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIendPoint.class);
    }

    public static RetrofitService getInstance() {
        if (service == null) {
            service = new RetrofitService();
        }
        return service;
    }

    public Call<MouResponse> getMou() {
        return api.getMou(Constants.API_KEY);
    }
    public Call<RealResponse> getReal() {
        return api.getReal(Constants.API_KEY);
    }
//    public Call<GenreResponse> getGenre() { return api.getGenre(Constants.API_KEY); }
//    public Call<GenreResponse> getGenreTv() { return api.getGenreTv(Constants.API_KEY); }
//    public Call<TvShowResponse> getTvs() { return api.getTvs(Constants.API_KEY); }
}
