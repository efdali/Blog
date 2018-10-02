package com.efdalincesu.blog.Models;

import com.google.gson.annotations.SerializedName;

public class Member {

    @SerializedName("uyeNick")
    private String uyeNick;

    @SerializedName("uyeResim")
    private String uyeResim;

    public String getUyeNick() {
        return uyeNick;
    }

    public void setUyeNick(String uyeNick) {
        this.uyeNick = uyeNick;
    }

    public String getUyeResim() {
        return uyeResim;
    }

    public void setUyeResim(String uyeResim) {
        this.uyeResim = uyeResim;
    }

    @Override
    public String toString() {
        return
                "Member{" +
                        "uyeNick = '" + uyeNick + '\'' +
                        ",uyeResim = '" + uyeResim + '\'' +
                        "}";
    }
}