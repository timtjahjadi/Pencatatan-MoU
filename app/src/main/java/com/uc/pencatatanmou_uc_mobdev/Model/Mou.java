package com.uc.pencatatanmou_uc_mobdev.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Mou implements Parcelable {

//    private String id, userid, title, form, date, photo, attach;

    @SerializedName("id")
    private String id;

    @SerializedName("userid")
    private String userid;

    @SerializedName("title")
    private String title;

    @SerializedName("form")
    private String form;

    @SerializedName("date")
    private String date;

    @SerializedName("photo")
    private String photo;

    @SerializedName("attach")
    private String attach;

    public Mou(){}

    public Mou(String id, String userid, String title, String form, String date, String photo, String attach) {
        this.id = id;
        this.userid = userid;
        this.title = title;
        this.form = form;
        this.date = date;
        this.photo = photo;
        this.attach = attach;
    }

    protected Mou(Parcel in) {
        id = in.readString();
        userid = in.readString();
        title = in.readString();
        form = in.readString();
        date = in.readString();
        photo = in.readString();
        attach = in.readString();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userid);
        dest.writeString(title);
        dest.writeString(form);
        dest.writeString(date);
        dest.writeString(photo);
        dest.writeString(attach);
    }
}
