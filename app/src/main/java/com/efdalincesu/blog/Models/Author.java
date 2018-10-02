package com.efdalincesu.blog.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Author {

    @SerializedName("yazarPuan")
    private String yazarPuan;

    @SerializedName("yazarNick")
    private String yazarNick;

    @SerializedName("yazilar")
    private List<Article> yazilar;

    @SerializedName("yazBasTarih")
    private String yazBasTarih;

    @SerializedName("yazarResim")
    private String yazarResim;

    public String getYazarPuan() {
        return yazarPuan;
    }

    public void setYazarPuan(String yazarPuan) {
        this.yazarPuan = yazarPuan;
    }

    public String getYazarNick() {
        return yazarNick;
    }

    public void setYazarNick(String yazarNick) {
        this.yazarNick = yazarNick;
    }

    public List<Article> getYazilar() {
        return yazilar;
    }

    public void setYazilar(List<Article> yazilar) {
        this.yazilar = yazilar;
    }

    public String getYazBasTarih() {
        return yazBasTarih;
    }

    public void setYazBasTarih(String yazBasTarih) {
        this.yazBasTarih = yazBasTarih;
    }

    public String getYazarResim() {
        return yazarResim;
    }

    public void setYazarResim(String yazarResim) {
        this.yazarResim = yazarResim;
    }

    @Override
    public String toString() {
        return
                "Author{" +
                        "yazarPuan = '" + yazarPuan + '\'' +
                        ",yazarNick = '" + yazarNick + '\'' +
                        ",yazilar = '" + yazilar + '\'' +
                        ",yazBasTarih = '" + yazBasTarih + '\'' +
                        ",yazarResim = '" + yazarResim + '\'' +
                        "}";
    }
}