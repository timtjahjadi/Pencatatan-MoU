package com.uc.pencatatanmou_uc_mobdev.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MouResponse {

    @SerializedName("results")
    private List<Mou> mouResult;

    public List<Mou> getMouResult() {
        return mouResult;
    }

}
