package com.gorontalo.laundryapp.Model;

public class DataModel {

    private int id_warna;
    private String warna;
    private String suara;

    public String getKode_warna() {
        return kode_warna;
    }

    public void setKode_warna(String kode_warna) {
        this.kode_warna = kode_warna;
    }

    private String kode_warna;

    public int getId_warna() {
        return id_warna;
    }

    public void setId_warna(int id_warna) {
        this.id_warna = id_warna;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getSuara() {
        return suara;
    }

    public void setSuara(String suara) {
        this.suara = suara;
    }
}
