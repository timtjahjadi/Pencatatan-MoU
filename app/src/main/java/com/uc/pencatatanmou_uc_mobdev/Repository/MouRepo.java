package com.uc.pencatatanmou_uc_mobdev.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.MouResponse;
import com.uc.pencatatanmou_uc_mobdev.Network.APIendPoint;
import com.uc.pencatatanmou_uc_mobdev.Network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MouRepo {
    private static MouRepo mouRepo;
    private RetrofitService service;
    private APIendPoint apIendPoint;
    private static final String TAG = "MouRepo";

    private MouRepo() {
        service = RetrofitService.getInstance();
    }

    public static MouRepo getInstance() {
        if (mouRepo == null) {
            mouRepo = new MouRepo();
        }
        return mouRepo;
    }

    public MutableLiveData<List<Mou>> getMouCollection() {
        MutableLiveData<List<Mou>> listMou = new MutableLiveData<>();

        service.getMou().enqueue(new Callback<MouResponse>() {
            @Override
            public void onResponse(Call<MouResponse> call, Response<MouResponse> response) {
                Log.d(TAG, "TES TES: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listMou.postValue(response.body().getMouResult());
                    }
                }
            }

            @Override
            public void onFailure(Call<MouResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listMou;
    }
}
