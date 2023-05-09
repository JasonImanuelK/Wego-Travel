package com.example.wego_travel

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.Pengguna
import org.json.JSONException

class IsiDataPesanPesawatActivity : AppCompatActivity(), View.OnClickListener  {
    private val pengguna = Pengguna.getInstance()
    companion object {
        const val KURSI_PESAWAT = "kursi_pesawat"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_isi_data_pesan_pesawat)

        val btnPesanPesawat: Button = findViewById(R.id.pesan_pesawat)
        btnPesanPesawat.setOnClickListener(this)

        val btnBatalPesanPesawat: Button = findViewById(R.id.batal_pesan_pesawat)
        btnBatalPesanPesawat.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.batal_pesan_pesawat ->{
                val navBatalPesanPesawat = Intent(this, MenuActivity::class.java)
                startActivity(navBatalPesanPesawat)
            }
            R.id.pesan_pesawat -> {
                val edtIdVoucher : EditText = findViewById(R.id.voucher_pesan_pesawat)
                val edtNamaDepan : EditText = findViewById(R.id.nama_depan_pesan_pesawat)
                val edtNamaBelakang : EditText = findViewById(R.id.nama_belakang_pesan_pesawat)
                val radioGroupJenisKelamin = findViewById<RadioGroup>(R.id.pesan_pesawat_gender_radio_group)
                val selectedRadioButtonId = radioGroupJenisKelamin.checkedRadioButtonId
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedRadioButtonText = selectedRadioButton.text.toString()
                val edtTanggalLahir : EditText = findViewById(R.id.tanggallahir_pesan_pesawat)
                val edtEmail : EditText = findViewById(R.id.email_pesan_pesawat)
                val edtNomorTelepon : EditText = findViewById(R.id.nomor_telepon_pesan_pesawat)
                createPesanPesawat(edtIdVoucher.text.toString(), edtNamaDepan.text.toString(), edtNamaBelakang.text.toString(), selectedRadioButtonText, edtTanggalLahir.text.toString(), edtEmail.text.toString(), edtNomorTelepon.text.toString())
            }
        }
    }

    fun createPesanPesawat(id_voucher: String, nama_depan: String, nama_belakang: String, jenis_kelamin: String, tanggal_lahir:String, email:String, nomor_telepon:String) {
        val nomor_kursi = intent.getStringExtra(KURSI_PESAWAT)
        val requestBody = "nomor_kursi="+nomor_kursi.toString()+"&id_pengguna="+pengguna.id_pengguna.toString()+"&id_voucher="+id_voucher+"&nama_depan="+nama_depan+"&nama_belakang="+nama_belakang+"&jenis_kelamin="+jenis_kelamin+"&tanggal_lahir="+tanggal_lahir+"&email="+email+"&nomor_telepon="+nomor_telepon

        val requestQueue = Volley.newRequestQueue(this)
        val url = "http://"+getString(R.string.ip_address)+":8080/Pesawat/Kursi"
        val uri = Uri.parse(url).buildUpon()
            .build()
        val stringRequest = object : StringRequest(
            Request.Method.POST, uri.toString(),
            { response ->
                try {
                    val moveToMenu = Intent(this, MenuActivity::class.java)
                    startActivity(moveToMenu)
                    Toast.makeText(this, "Pesan Pesawat Berhasil!", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, "Pesan Pesawat Gagal!", Toast.LENGTH_LONG).show()
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