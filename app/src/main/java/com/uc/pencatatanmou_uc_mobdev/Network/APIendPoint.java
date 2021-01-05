package com.uc.pencatatanmou_uc_mobdev.Network;

import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.MouResponse;
import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.Model.RealResponse;
import com.uc.pencatatanmou_uc_mobdev.Model.User;
import com.uc.pencatatanmou_uc_mobdev.Model.UserResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIendPoint {

    //MOU
    @GET("api/api-mou")
    Call<MouResponse> getMou();

    @FormUrlEncoded
    @POST("api/api-mou")
    Call<Mou> setMou(
            @Field("userid") String userid,
            @Field("title") String title,
            @Field("form") String form,
            @Field("date") String date,
            @Field("photo") String photo,
            @Field("attach") String attach
    );

    @DELETE("/api/api-mou/{id}")
    Call<Mou> deleteMou(@Path("id") String itemId);

    @FormUrlEncoded
    @PUT("api/api-mou/{id}")
    Call<Mou> updateMou(
            @Path("id") String itemId,
            @Field("title") String title,
            @Field("form") String form
    );


    //REAL
    @GET("api/api-real")
    Call<RealResponse> getReal();

    @FormUrlEncoded
    @POST("api/api-real")
    Call<Real> setReal(
            @Field("userid") String userid,
            @Field("name") String title,
            @Field("desc") String form,
            @Field("date") String date,
            @Field("mouid") String mouid
    );

    @DELETE("/api/api-real/{id}")
    Call<Real> deleteReal(@Path("id") String itemId);

    @FormUrlEncoded
    @PUT("api/api-real/{id}")
    Call<Real> updateReal(
            @Path("id") String itemId,
            @Field("name") String title,
            @Field("desc") String form,
            @Field("mouid") String mouid
    );

    //AUTH
    @GET("api/api-auth")
    Call<UserResponse> getUser();

    @FormUrlEncoded
    @PUT("api/api-auth/{id}")
    Call<User> updateUser(
            @Path("id") String itemId,
            @Field("name") String name,
            @Field("email") String email
    );

    //FILE
//    @GET("api/api-file/{id}")
//    Call<> getFile(@Path("id") String itemId);

    @Multipart
    @POST("api/api-file/{id}")
    Call<ResponseBody> setFile(
            @Path("id") String itemId,
            @Part MultipartBody.Part part
    );
}
