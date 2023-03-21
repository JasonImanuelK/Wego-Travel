package com.example.wego_travel.Models;

import java.sql.Time;
import java.util.Date;

public class Pesawat {
    private int id_pesawat;
    private String maskapai;
    private String tujuan_berangkat;
    private String asal_berangkat;
    private Date tanggal_berangkat;
    private java.sql.Time jam_berangkat;
    private double promo;

    public Pesawat(int id_pesawat, String maskapai, String tujuan_berangkat, String asal_berangkat, Date tanggal_berangkat, Time jam_berangkat, double promo) {
        this.id_pesawat = id_pesawat;
        this.maskapai = maskapai;
        this.tujuan_berangkat = tujuan_berangkat;
        this.asal_berangkat = asal_berangkat;
        this.tanggal_berangkat = tanggal_berangkat;
        this.jam_berangkat = jam_berangkat;
        this.promo = promo;
    }

    public int getId_pesawat() {
        return id_pesawat;
    }

    public void setId_pesawat(int id_pesawat) {
        this.id_pesawat = id_pesawat;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getTujuan_berangkat() {
        return tujuan_berangkat;
    }

    public void setTujuan_berangkat(String tujuan_berangkat) {
        this.tujuan_berangkat = tujuan_berangkat;
    }

    public String getAsal_berangkat() {
        return asal_berangkat;
    }

    public void setAsal_berangkat(String asal_berangkat) {
        this.asal_berangkat = asal_berangkat;
    }

    public Date getTanggal_berangkat() {
        return tanggal_berangkat;
    }

    public void setTanggal_berangkat(Date tanggal_berangkat) {
        this.tanggal_berangkat = tanggal_berangkat;
    }

    public Time getJam_berangkat() {
        return jam_berangkat;
    }

    public void setJam_berangkat(Time jam_berangkat) {
        this.jam_berangkat = jam_berangkat;
    }

    public double getPromo() {
        return promo;
    }

    public void setPromo(double promo) {
        this.promo = promo;
    }
}