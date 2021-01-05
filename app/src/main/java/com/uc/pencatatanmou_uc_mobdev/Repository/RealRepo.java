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
    MutableLiveData<Integer> returning;

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

    public MutableLiveData<Integer> postReal(String userid, String name, String desc, String date, String mouid) {
        final MutableLiveData<Integer>[] returning = new MutableLiveData[]{new MutableLiveData<>()};

        service.createReal(userid, name, desc, date, mouid).enqueue(new Callback<Real>() {
            @Override
            public void onResponse(Call<Real> call, Response<Real> response) {
                returning[0].setValue(0);
            }

            @Override
            public void onFailure(Call<Real> call, Throwable t) {
                returning[0].setValue(1);
            }
        });

        return returning[0];

    }

    public MutableLiveData<Integer> deleteReal(String id) {
        final MutableLiveData<Integer>[] returning = new MutableLiveData[]{new MutableLiveData<>()};

        service.removeReal(id).enqueue(new Callback<Real>() {
            @Override
            public void onResponse(Call<Real> call, Response<Real> response) {
                returning[0].setValue(0);
            }

            @Override
            public void onFailure(Call<Real> call, Throwable t) {
                returning[0].setValue(1);
            }
        });

        return returning[0];

    }

    public MutableLiveData<Integer> updateReal(String id, String name, String desc, String mouid) {
        final MutableLiveData<Integer>[] returning = new MutableLiveData[]{new MutableLiveData<>()};

        service.updateReal(id, name, desc, mouid).enqueue(new Callback<Real>() {
            @Override
            public void onResponse(Call<Real> call, Response<Real> response) {
                returning[0].setValue(0);
            }

            @Override
            public void onFailure(Call<Real> call, Throwable t) {
                returning[0].setValue(1);
            }
        });

        return returning[0];

    }
}
