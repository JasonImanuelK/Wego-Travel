package com.example.wego_travel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import java.text.SimpleDateFormat

class FilterHotelActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_hotel)

        val btnCari: Button = findViewById(R.id.btn_cari_hotel)
        btnCari.setOnClickListener(this)

        val btnKembali: Button = findViewById(R.id.btn_kembali_filter_hotel)
        btnKembali.setOnClickListener(this)

        val listKota = resources.getStringArray(R.array.kota)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listKota)
        val kota : AutoCompleteTextView = findViewById(R.id.kota_filter)
        kota.setAdapter(arrayAdapter)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.btn_cari_hotel ->{
                val edtKota : EditText = findViewById(R.id.kota_filter)
                val edtTanggalInap : EditText = findViewById(R.id.tanggal_inap_filter)
                val edtAkhirInap : EditText = findViewById(R.id.akhir_inap_filter)
                val edtJumlahPenginap : EditText = findViewById(R.id.jumlah_penginap_filter)
                val moveWithDataIntent = Intent(this@FilterHotelActivity, PesanHotelActivity::class.java)

                moveWithDataIntent.putExtra(PesanHotelActivity.KOTA, edtKota.text.toString())
                moveWithDataIntent.putExtra(PesanHotelActivity.TANGGAL_INAP, edtTanggalInap.text.toString())
                moveWithDataIntent.putExtra(PesanHotelActivity.AKHIR_INAP, edtAkhirInap.text.toString())
                moveWithDataIntent.putExtra(PesanHotelActivity.JUMLAH_PENGINAP, edtJumlahPenginap.text.toString())
                startActivity(moveWithDataIntent)
            }
            R.id.btn_kembali_filter_hotel -> {
                val navKembali = Intent(this, MenuActivity::class.java)
                startActivity(navKembali)
            }
        }
    }
}