package com.uc.pencatatanmou_uc_mobdev.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.MouResponse;
import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.Model.RealResponse;
import com.uc.pencatatanmou_uc_mobdev.Network.APIendPoint;
import com.uc.pencatatanmou_uc_mobdev.Network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RealRepo {
    private static RealRepo realRepo;
    private RetrofitService service;
    private APIendPoint apIendPoint;
    private static final String TAG = "RealRepo";

    private RealRepo() {
        service = RetrofitService.getInstance();
    }

    public static RealRepo getInstance() {
        if (realRepo == null) {
            realRepo = new RealRepo();
        }
        return realRepo;
    }

    public MutableLiveData<List<Real>> getRealCollection() {
        MutableLiveData<List<Real>> listReal = new MutableLiveData<>();

        service.getReal().enqueue(new Callback<RealResponse>() {
            @Override
            public void onResponse(Call<RealResponse> call, Response<RealResponse> response) {
                Log.d(TAG, "TES TES: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listReal.postValue(response.body().getRealResult());
                    }
                }
            }

            @Override
            public void onFailure(Call<RealResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listReal;
    }
}
