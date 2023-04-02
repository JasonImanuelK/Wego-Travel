package com.example.wego_travel.Models;

public class Voucher {
    private int id_voucher;
    private String nama_voucher;
    private double nilai;
    private tipe_Tiket tipeTiket;
    private status_pengguna statusPengguna;
    private Pengguna pengguna;

    public Voucher(int id_voucher, String nama_voucher, double nilai, tipe_Tiket tipeTiket, status_pengguna statusPengguna, Pengguna pengguna){
        this.id_voucher = id_voucher;
        this.nama_voucher = nama_voucher;
        this.nilai = nilai;
        this.tipeTiket = tipeTiket;
        this.statusPengguna = statusPengguna;
        this.pengguna = pengguna;
    }

    // id voucher
    public int getId_voucher(){
        return id_voucher;
    }

    public void setId_voucher(int id_voucher){
        this.id_voucher = id_voucher;
    }

    public String getNama_voucher() {
        return nama_voucher;
    }

    public void setNama_voucher(String nama_voucher) {
        this.nama_voucher = nama_voucher;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public tipe_Tiket getTipeTiket() {
        return tipeTiket;
    }

    public void setTipeTiket(tipe_Tiket tipeTiket) {
        this.tipeTiket = tipeTiket;
    }

    public status_pengguna getStatusPengguna() {
        return statusPengguna;
    }

    public void setStatusPengguna(status_pengguna statusPengguna) {
        this.statusPengguna = statusPengguna;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
