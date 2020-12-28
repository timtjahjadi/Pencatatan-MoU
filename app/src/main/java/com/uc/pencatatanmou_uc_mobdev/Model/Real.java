package com.uc.pencatatanmou_uc_mobdev.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Real implements Parcelable {

    //    private String id, name, desc, date, mou_id;

    @SerializedName("title")
    private String title;

    public Real(){}

    public Real(String title) {
        this.title = title;
    }

    protected Real(Parcel in) {
        title = in.readString();
    }

    public static final Creator<Real> CREATOR = new Creator<Real>() {
        @Override
        public Real createFromParcel(Parcel in) {
            return new Real(in);
        }

        @Override
        public Real[] newArray(int size) {
            return new Real[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}
