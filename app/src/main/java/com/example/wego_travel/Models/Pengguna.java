package com.example.wego_travel.Models;

import java.util.Date;

public class Pengguna {
    private int id_pengguna;
    private String name;
    private String email;
    private String password;
    private Jenis_Kelamin jenisKelamin;
    private Date tanggalLahir;
    private String nomorTelepon;
    private String alamat;

    public Pengguna(int id_pengguna, String name, String email, String password, Jenis_Kelamin jenisKelamin, Date tanggalLahir, String nomorTelepon, String alamat) {
        this.id_pengguna = id_pengguna;
        this.name = name;
        this.email = email;
        this.password = password;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
    }

    // id pengguna
    public int getIdPengguna(){
        return id_pengguna;
    }

    public void setId_pengguna(int id_pengguna){
        this.id_pengguna = id_pengguna;
    }

    // name
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    // email
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    // password
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    // jenis kelamin
    public Jenis_Kelamin getJenisKelamin(){
        return jenisKelamin;
    }

    public void setJenisKelamin(Jenis_Kelamin jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    // tanggal lahir
    public Date getTanggalLahir(){
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir){
        this.tanggalLahir = tanggalLahir;
    }

    // nomor telepon s
    public String getNomorTelepon(){
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon){
        this.nomorTelepon = nomorTelepon;
    }

    // alamat s
    public String setAlamat(){
        return alamat;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
}
