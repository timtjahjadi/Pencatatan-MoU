package com.uc.pencatatanmou_uc_mobdev.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Mou implements Parcelable {

//    private String id, title, form, date;

    @SerializedName("title")
    private String title;

    public Mou(){}

    public Mou(String title) {
        this.title = title;
    }

    protected Mou(Parcel in) {
        title = in.readString();
    }

    public static final Creator<Mou> CREATOR = new Creator<Mou>() {
        @Override
        public Mou createFromParcel(Parcel in) {
            return new Mou(in);
        }

        @Override
        public Mou[] newArray(int size) {
            return new Mou[size];
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
