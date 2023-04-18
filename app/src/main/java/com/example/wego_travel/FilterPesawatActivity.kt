package com.example.wego_travel

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import android.widget.EditText

class FilterPesawatActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_pesawat)

        val btnCari: Button = findViewById(R.id.btn_cari_pesawat)
        btnCari.setOnClickListener(this)

        val btnKembali: Button = findViewById(R.id.btn_kembali_filter_pesawat)
        btnKembali.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.btn_cari_pesawat ->{
                val edtTempatKeberangkatan : EditText = findViewById(R.id.tempat_berangkat_filter)
                val edtTujuanKeberangkatan : EditText = findViewById(R.id.tujuan_berangkat_filter)
                val edtTanggalKeberangkatan : EditText = findViewById(R.id.tanggal_berangkat_filter)
                val moveWithDataIntent = Intent(this@FilterPesawatActivity, PesanPesawatActivity::class.java)

                moveWithDataIntent.putExtra(PesanPesawatActivity.ASAL, edtTempatKeberangkatan.text.toString())
                moveWithDataIntent.putExtra(PesanPesawatActivity.TUJUAN, edtTujuanKeberangkatan.text.toString())
                moveWithDataIntent.putExtra(PesanPesawatActivity.TANGGAL, edtTanggalKeberangkatan.text.toString())
                startActivity(moveWithDataIntent)
            }
            R.id.btn_kembali_filter_pesawat -> {
                val navKembali = Intent(this, MenuActivity::class.java)
                startActivity(navKembali)
            }
        }
    }
}