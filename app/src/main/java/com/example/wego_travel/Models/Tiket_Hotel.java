package com.example.wego_travel.Models;

import java.util.Date;

public class Tiket_Hotel extends Tiket{
    private int id_tiket_hotel;
    private String nama_penginap;
    private Jenis_Kelamin jenisKelamin;
    private Date tanggalLahir;
    private Date tanggalInap;
    private int lamaInap;

    public Tiket_Hotel(int id_tiket_hotel, String nama_penginap, Jenis_Kelamin jenisKelamin, Date tanggalLahir, Date tanggalInap, int lamaInap) {
        this.id_tiket_hotel = id_tiket_hotel;
        this.nama_penginap = nama_penginap;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
        this.tanggalInap = tanggalInap;
        this.lamaInap = lamaInap;
    }

    public int getId_tiket_hotel() {
        return id_tiket_hotel;
    }

    public void setId_tiket_hotel(int id_tiket_hotel) {
        this.id_tiket_hotel = id_tiket_hotel;
    }

    public String getNama_penginap() {
        return nama_penginap;
    }

    public void setNama_penginap(String nama_penginap) {
        this.nama_penginap = nama_penginap;
    }

    public Jenis_Kelamin getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Jenis_Kelamin jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Date getTanggalInap() {
        return tanggalInap;
    }

    public void setTanggalInap(Date tanggalInap) {
        this.tanggalInap = tanggalInap;
    }

    public int getLamaInap() {
        return lamaInap;
    }

    public void setLamaInap(int lamaInap) {
        this.lamaInap = lamaInap;
    }
}
