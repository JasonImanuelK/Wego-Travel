package com.example.wego_travel.Models;

public class Hotel {
    private int id_hotel;
    private String nama_hotel;
    private String alamat_hotel;
    private String deskripsi;
    private int rating;
    private double promo;

    public Hotel(int id_hotel, String nama_hotel, String alamat_hotel, String deskripsi, int rating, double promo) {
        this.id_hotel = id_hotel;
        this.nama_hotel = nama_hotel;
        this.alamat_hotel = alamat_hotel;
        this.deskripsi = deskripsi;
        this.rating = rating;
        this.promo = promo;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getNama_hotel() {
        return nama_hotel;
    }

    public void setNama_hotel(String nama_hotel) {
        this.nama_hotel = nama_hotel;
    }

    public String getAlamat_hotel() {
        return alamat_hotel;
    }

    public void setAlamat_hotel(String alamat_hotel) {
        this.alamat_hotel = alamat_hotel;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getPromo() {
        return promo;
    }

    public void setPromo(double promo) {
        this.promo = promo;
    }
}
