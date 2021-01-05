package com.uc.pencatatanmou_uc_mobdev.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.MouResponse;
import com.uc.pencatatanmou_uc_mobdev.Network.APIendPoint;
import com.uc.pencatatanmou_uc_mobdev.Network.RetrofitService;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MouRepo {
    private static MouRepo mouRepo;
    private RetrofitService service;
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

    public MutableLiveData<Integer> postMou(String userid, String  name, String form, String date, String photo, String attach) {
        final MutableLiveData<Integer>[] returning = new MutableLiveData[]{new MutableLiveData<>()};

        service.createMou(userid, name, form, date, photo, attach).enqueue(new Callback<Mou>() {
            @Override
            public void onResponse(Call<Mou> call, Response<Mou> response) {
                returning[0].setValue(0);
            }

            @Override
            public void onFailure(Call<Mou> call, Throwable t) {
                returning[0].setValue(1);
            }
        });

        return returning[0];

    }

    public MutableLiveData<Integer> deleteMou(String id) {
        final MutableLiveData<Integer>[] returning = new MutableLiveData[]{new MutableLiveData<>()};

        service.removeMou(id).enqueue(new Callback<Mou>() {
            @Override
            public void onResponse(Call<Mou> call, Response<Mou> response) {
                returning[0].setValue(0);
            }

            @Override
            public void onFailure(Call<Mou> call, Throwable t) {
                returning[0].setValue(1);
            }
        });

        return returning[0];

    }

    public MutableLiveData<Integer> updateMou(String id, String  name, String form) {
        final MutableLiveData<Integer>[] returning = new MutableLiveData[]{new MutableLiveData<>()};

        service.updateMou(id, name, form).enqueue(new Callback<Mou>() {
            @Override
            public void onResponse(Call<Mou> call, Response<Mou> response) {
                returning[0].setValue(0);
            }

            @Override
            public void onFailure(Call<Mou> call, Throwable t) {
                returning[0].setValue(1);
            }
        });

        return returning[0];

    }

    public MutableLiveData<Integer> uploadFile(String id, MultipartBody.Part part) {
        final MutableLiveData<Integer>[] returning = new MutableLiveData[]{new MutableLiveData<>()};

        service.uploadFile(id, part).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                returning[0].setValue(0);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                returning[0].setValue(1);
            }
        });

        return returning[0];

    }
}
