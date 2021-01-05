package com.uc.pencatatanmou_uc_mobdev.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("data")
    private List<User> userResult;

    public List<User> getUserResult() {
        return userResult;
    }

    public void setUserResult(List<User> userResult) {
        this.userResult = userResult;
    }
}
