package com.uc.pencatatanmou_uc_mobdev.Network;

import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.MouResponse;
import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.Model.RealResponse;
import com.uc.pencatatanmou_uc_mobdev.Model.User;
import com.uc.pencatatanmou_uc_mobdev.Model.UserResponse;
import com.uc.pencatatanmou_uc_mobdev.util.Constants;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    //MOU
    public Call<MouResponse> getMou() {
        return api.getMou();
    }
    public Call<Mou> createMou(String userid, String name, String form, String date, String photo, String attach) {
        return api.setMou(userid, name, form, date, photo, attach);
    }
    public Call<Mou> removeMou(String id) {
        return api.deleteMou(id);
    }
    public Call<Mou> updateMou(String id, String name, String form) {
        return api.updateMou(id, name, form);
    }

    //REAL
    public Call<RealResponse> getReal() {
        return api.getReal();
    }
    public Call<Real> createReal(String userid, String name, String desc, String date, String mouid) {
        return api.setReal(userid, name, desc, date, mouid);
    }
    public Call<Real> removeReal(String id) {
        return api.deleteReal(id);
    }
    public Call<Real> updateReal(String id, String name, String desc, String mouid) {
        return api.updateReal(id, name, desc, mouid);
    }

    //PROFILE
    public Call<UserResponse> getUser() {
        return api.getUser();
    }
    public Call<User> updateUser(String id, String name, String email) {
        return api.updateUser(id, name, email);
    }

    //FILE
    public Call<ResponseBody> uploadFile(String id, MultipartBody.Part part) {
        return api.setFile(id, part);
    }

}
