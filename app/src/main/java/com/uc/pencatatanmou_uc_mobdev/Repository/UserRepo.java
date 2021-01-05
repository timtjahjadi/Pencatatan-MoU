package com.uc.pencatatanmou_uc_mobdev.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.Model.RealResponse;
import com.uc.pencatatanmou_uc_mobdev.Model.User;
import com.uc.pencatatanmou_uc_mobdev.Model.UserResponse;
import com.uc.pencatatanmou_uc_mobdev.Network.APIendPoint;
import com.uc.pencatatanmou_uc_mobdev.Network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepo {
    private static UserRepo userRepo;
    private RetrofitService service;
    private APIendPoint apIendPoint;
    private static final String TAG = "UserRepo";
    MutableLiveData<Integer> returning;

    private UserRepo() {
        service = RetrofitService.getInstance();
    }

    public static UserRepo getInstance() {
        if (userRepo == null) {
            userRepo = new UserRepo();
        }
        return userRepo;
    }

    public MutableLiveData<List<User>> getUserCollection() {
        MutableLiveData<List<User>> listUser = new MutableLiveData<>();

        service.getUser().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.d(TAG, "TES TES: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listUser.postValue(response.body().getUserResult());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listUser;
    }

    public MutableLiveData<Integer> updateUser(String id, String name, String email) {
        final MutableLiveData<Integer>[] returning = new MutableLiveData[]{new MutableLiveData<>()};

        service.updateUser(id, name, email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                returning[0].setValue(0);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                returning[0].setValue(1);
            }
        });

        return returning[0];

    }
}
