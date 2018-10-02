package com.efdalincesu.blog.Models;

import com.google.gson.annotations.SerializedName;

public class ArticleDetails {

    @SerializedName("yaziTarih")
    private String yaziTarih;

    @SerializedName("yazarId")
    private String yazarId;

    @SerializedName("kategoriId")
    private String kategoriId;

    @SerializedName("kategoriAdi")
    private String kategoriAdi;

    @SerializedName("yazarAdi")
    private String yazarAdi;

    @SerializedName("yaziIcerik")
    private String yaziIcerik;

    @SerializedName("yaziId")
    private String yaziId;

    @SerializedName("yaziBaslik")
    private String yaziBaslik;

    @SerializedName("yaziResim")
    private String yaziResim;

    @SerializedName("begeniSayisi")
    private String begeniSayisi;

    public String getYaziTarih() {
        return yaziTarih;
    }

    public void setYaziTarih(String yaziTarih) {
        this.yaziTarih = yaziTarih;
    }

    public String getYazarId() {
        return yazarId;
    }

    public void setYazarId(String yazarId) {
        this.yazarId = yazarId;
    }

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

    public String getYazarAdi() {
        return yazarAdi;
    }

    public void setYazarAdi(String yazarAdi) {
        this.yazarAdi = yazarAdi;
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

    public String getBegeniSayisi() {
        return begeniSayisi;
    }

    public void setBegeniSayisi(String begeniSayisi) {
        this.begeniSayisi = begeniSayisi;
    }

    @Override
    public String toString() {
        return
                "ArticleDetails{" +
                        "yaziTarih = '" + yaziTarih + '\'' +
                        ",yazarId = '" + yazarId + '\'' +
                        ",kategoriId = '" + kategoriId + '\'' +
                        ",kategoriAdi = '" + kategoriAdi + '\'' +
                        ",yazarAdi = '" + yazarAdi + '\'' +
                        ",yaziIcerik = '" + yaziIcerik + '\'' +
                        ",yaziId = '" + yaziId + '\'' +
                        ",yaziBaslik = '" + yaziBaslik + '\'' +
                        ",yaziResim = '" + yaziResim + '\'' +
                        ",begeniSayisi = '" + begeniSayisi + '\'' +
                        "}";
    }
}