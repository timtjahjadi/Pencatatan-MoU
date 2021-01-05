package com.uc.pencatatanmou_uc_mobdev.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Real implements Parcelable {

    //    private String id, userid, name, desc, date, mouid;

    @SerializedName("id")
    private String id;

    @SerializedName("userid")
    private String userid;

    @SerializedName("name")
    private String name;

    @SerializedName("desc")
    private String desc;

    @SerializedName("date")
    private String date;

    @SerializedName("mouid")
    private String mouid;

    public Real(){}

    public Real(String id, String userid, String name, String desc, String date, String mouid) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.mouid = mouid;
    }

    protected Real(Parcel in) {
        id = in.readString();
        userid = in.readString();
        name = in.readString();
        desc = in.readString();
        date = in.readString();
        mouid = in.readString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMouid() {
        return mouid;
    }

    public void setMouid(String mouid) {
        this.mouid = mouid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userid);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(date);
        dest.writeString(mouid);
    }
}
