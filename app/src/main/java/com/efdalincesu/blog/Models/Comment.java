package com.efdalincesu.blog.Models;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("yorumIcerik")
    private String yorumIcerik;

    @SerializedName("kullanici")
    private Member kullanici;

    @SerializedName("yorumTarih")
    private String yorumTarih;

    public String getYorumIcerik() {
        return yorumIcerik;
    }

    public void setYorumIcerik(String yorumIcerik) {
        this.yorumIcerik = yorumIcerik;
    }

    public Member getKullanici() {
        return kullanici;
    }

    public void setKullanici(Member kullanici) {
        this.kullanici = kullanici;
    }

    public String getYorumTarih() {
        return yorumTarih;
    }

    public void setYorumTarih(String yorumTarih) {
        this.yorumTarih = yorumTarih;
    }

    @Override
    public String toString() {
        return
                "Comment{" +
                        "yorumIcerik = '" + yorumIcerik + '\'' +
                        ",kullanici = '" + kullanici + '\'' +
                        ",yorumTarih = '" + yorumTarih + '\'' +
                        "}";
    }
}