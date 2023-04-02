package com.example.wego_travel.Models;

import java.util.Date;

public class Tiket_Pesawat extends Tiket{
    private int id_tiket_pesawat;
    private String nama_depan;
    private String nama_belakang;
    private Jenis_Kelamin jenisKelamin;
    private Date tanggalLahir;
    private String email;
    private String nomorTelepon;

    public Tiket_Pesawat(int id_tiket_pesawat, String nama_depan, String nama_belakang, Jenis_Kelamin jenisKelamin, Date tanggalLahir, String email, String nomorTelepon) {
        this.id_tiket_pesawat = id_tiket_pesawat;
        this.nama_depan = nama_depan;
        this.nama_belakang = nama_belakang;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
        this.email = email;
        this.nomorTelepon = nomorTelepon;
    }

    public int getId_tiket_pesawat() {
        return id_tiket_pesawat;
    }

    public void setId_tiket_pesawat(int id_tiket_pesawat) {
        this.id_tiket_pesawat = id_tiket_pesawat;
    }

    public String getNama_depan() {
        return nama_depan;
    }

    public void setNama_depan(String nama_depan) {
        this.nama_depan = nama_depan;
    }

    public String getNama_belakang() {
        return nama_belakang;
    }

    public void setNama_belakang(String nama_belakang) {
        this.nama_belakang = nama_belakang;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }
}
