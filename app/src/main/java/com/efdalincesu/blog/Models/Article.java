package com.efdalincesu.blog.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Article implements Serializable{

    @SerializedName("yaziTarih")
    private String yaziTarih;

    @SerializedName("yorumSayisi")
    private String yorumSayisi;

    @SerializedName("yaziIcerik")
    private String yaziIcerik;

    @SerializedName("yaziId")
    private String yaziId;

    @SerializedName("yaziBaslik")
    private String yaziBaslik;

    @SerializedName("yaziResim")
    private String yaziResim;

    public String getYaziTarih() {
        return yaziTarih;
    }

    public void setYaziTarih(String yaziTarih) {
        this.yaziTarih = yaziTarih;
    }

    public String getYorumSayisi() {
        return yorumSayisi;
    }

    public void setYorumSayisi(String yorumSayisi) {
        this.yorumSayisi = yorumSayisi;
    }

    public String getYaziIcerik() {
        return yaziIcerik;
    }

    public void setYaziIcerik(String yaziIcerik) {
        this.yaziIcerik = yaziIcerik;
    }

    public String getYaziId() {
        return yaziId;
    }

    public void setYaziId(String yaziId) {
        this.yaziId = yaziId;
    }

    public String getYaziBaslik() {
        return yaziBaslik;
    }

    public void setYaziBaslik(String yaziBaslik) {
        this.yaziBaslik = yaziBaslik;
    }

    public String getYaziResim() {
        return yaziResim;
    }

    public void setYaziResim(String yaziResim) {
        this.yaziResim = yaziResim;
    }

    @Override
    public String toString() {
        return
                "Article{" +
                        "yaziTarih = '" + yaziTarih + '\'' +
                        ",yorumSayisi = '" + yorumSayisi + '\'' +
                        ",yaziIcerik = '" + yaziIcerik + '\'' +
                        ",yaziId = '" + yaziId + '\'' +
                        ",yaziBaslik = '" + yaziBaslik + '\'' +
                        ",yaziResim = '" + yaziResim + '\'' +
                        "}";
    }
}