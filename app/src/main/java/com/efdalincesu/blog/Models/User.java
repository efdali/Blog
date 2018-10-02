package com.efdalincesu.blog.Models;

import com.efdalincesu.blog.RestApi.BaseUrl;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("uyeNick")
    private String uyeNick;

    @SerializedName("uyeId")
    private String uyeId;

    @SerializedName("uyeResim")
    private String uyeResim;

    @SerializedName("uyeMail")
    private String uyeMail;

    public void set(){

        uyeNick="-1";
        uyeMail="-1";
        uyeResim="-1";
        uyeId="-1";

    }

    public String getUyeNick() {
        return uyeNick;
    }

    public void setUyeNick(String uyeNick) {
        this.uyeNick = uyeNick;
    }

    public String getUyeId() {
        return uyeId;
    }

    public void setUyeId(String uyeId) {
        this.uyeId = uyeId;
    }

    public String getUyeResim() {
        return uyeResim;
    }

    public void setUyeResim(String uyeResim) {
        this.uyeResim = BaseUrl.URL+uyeResim;
    }

    public String getUyeMail() {
        return uyeMail;
    }

    public void setUyeMail(String uyeMail) {
        this.uyeMail = uyeMail;
    }

    @Override
    public String toString() {
        return
                "User{" +
                        "uyeNick = '" + uyeNick + '\'' +
                        ",uyeId = '" + uyeId + '\'' +
                        ",uyeResim = '" + uyeResim + '\'' +
                        ",uyeMail = '" + uyeMail + '\'' +
                        "}";
    }
}