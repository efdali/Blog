package com.efdalincesu.blog.Models;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("kategoriId")
    private String kategoriId;

    @SerializedName("kategoriAdi")
    private String kategoriAdi;

    @SerializedName("kategoriResmi")
    private String kategoriResmi;

    public String getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(String kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getKategoriAdi() {
        return kategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdi = kategoriAdi;
    }

    public String getKategoriResmi() {
        return kategoriResmi;
    }

    public void setKategoriResmi(String kategoriResmi) {
        this.kategoriResmi = kategoriResmi;
    }

    @Override
    public String toString() {
        return
                "Category{" +
                        "kategoriId = '" + kategoriId + '\'' +
                        ",kategoriAdi = '" + kategoriAdi + '\'' +
                        ",kategoriResmi = '" + kategoriResmi + '\'' +
                        "}";
    }
}