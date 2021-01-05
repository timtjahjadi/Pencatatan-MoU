package com.uc.pencatatanmou_uc_mobdev.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RealResponse {

    @SerializedName("data")
    private List<Real> realResult;

    public List<Real> getRealResult() {
        return realResult;
    }
}
