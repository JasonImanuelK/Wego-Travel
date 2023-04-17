package com.example.wego_travel

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.Pengguna
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat

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
                val sdf = SimpleDateFormat("yyyy-MM-dd")

                cariPesawat(edtTempatKeberangkatan.text.toString(), edtTujuanKeberangkatan.text.toString(), edtTanggalKeberangkatan.text.toString())
            }
            R.id.btn_kembali_filter_pesawat -> {
                val navKembali = Intent(this, MenuActivity::class.java)
                startActivity(navKembali)
            }
        }
    }

    fun cariPesawat(tempat_berangkat: String, tujuan_berangkat: String, tanggal_berangkat:String) {
        val requestBody = "tempat_berangkat="+tempat_berangkat+"&tujuan_berangkat="+tujuan_berangkat+"&tanggal_berangkat"+tanggal_berangkat

        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/Pesawat/"+tempat_berangkat+"/"+tujuan_berangkat+"/"+tanggal_berangkat).buildUpon()
            .build()
        val stringRequest = object : StringRequest(
            Request.Method.POST, uri.toString(),
            { response ->
                try {
                    val gson = Gson()
                    val jsonObject = JSONObject(response)
                    val dataObject = jsonObject.getJSONObject("data")
                    val a = gson.fromJson(dataObject.toString(), Pengguna::class.java)
                    Log.v("Hasil : ", a.toString())
                    Pengguna.setInstance(a)
                    val login = Intent(this, MenuActivity::class.java)
                    startActivity(login)
                    Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, "Login Gagal!", Toast.LENGTH_LONG).show()
                error.printStackTrace()
            }) {
            @Throws
            override fun getBodyContentType(): String {
                // Set the content type to x-www-form-urlencoded
                return "application/x-www-form-urlencoded; charset=UTF-8"
            }

            override fun getBody(): ByteArray {
                // Convert the request body to a byte array
                return requestBody.toByteArray(Charsets.UTF_8)
            }
        }
        requestQueue.add(stringRequest)
    }
}