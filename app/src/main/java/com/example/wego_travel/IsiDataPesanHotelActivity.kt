package com.example.wego_travel

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.Pengguna
import org.json.JSONException

class IsiDataPesanHotelActivity : AppCompatActivity(), View.OnClickListener {
    private val pengguna = Pengguna.getInstance()
    companion object {
        const val NOMOR_KAMAR = "nomor_kamar"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_isi_data_pesan_hotel)

        val btnPesanHotel: Button = findViewById(R.id.pesan_hotel)
        btnPesanHotel.setOnClickListener(this)

        val btnBatalPesanHotel: Button = findViewById(R.id.batal_pesan_hotel)
        btnBatalPesanHotel.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.batal_pesan_hotel ->{
                val navBatalPesanHotel = Intent(this, MenuActivity::class.java)
                startActivity(navBatalPesanHotel)
            }
            R.id.pesan_hotel -> {
                val edtIdVoucher : EditText = findViewById(R.id.voucher_pesan_hotel)
                val edtNamaPenginap : EditText = findViewById(R.id.nama_pesan_hotel)
                val radioGroupJenisKelamin = findViewById<RadioGroup>(R.id.pesan_hotel_gender_radio_group)
                val selectedRadioButtonId = radioGroupJenisKelamin.checkedRadioButtonId
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedRadioButtonText = selectedRadioButton.text.toString()
                val edtTanggalLahir : EditText = findViewById(R.id.tanggallahir_pesan_hotel)
                val edtTanggalInap : EditText = findViewById(R.id.tanggal_inap_pesan_hotel)
                val edtLamaInap : EditText = findViewById(R.id.lama_inap_pesan_hotel)
                createPesanHotel(edtIdVoucher.text.toString(), edtNamaPenginap.text.toString(), selectedRadioButtonText, edtTanggalLahir.text.toString(), edtTanggalInap.text.toString(), edtLamaInap.text.toString())
            }
        }
    }

    fun createPesanHotel(id_voucher: String, nama_penginap: String, jenis_kelamin: String, tanggal_lahir: String, tanggal_inap:String, lama_inap:String) {
        val nomor_kamar = intent.getStringExtra(NOMOR_KAMAR)
        val requestBody = "nomor_kamar="+nomor_kamar.toString()+"&id_pengguna="+pengguna.id_pengguna.toString()+"&id_voucher="+id_voucher+"&nama_penginap="+nama_penginap+"&jenis_kelamin="+jenis_kelamin+"&tanggal_lahir="+tanggal_lahir+"&tanggal_inap="+tanggal_inap+"&lama_inap="+lama_inap

        val requestQueue = Volley.newRequestQueue(this)
        val url = "http://"+getString(R.string.ip_address)+":8080/Hotel/Kamar"
        val uri = Uri.parse(url).buildUpon()
            .build()
        val stringRequest = object : StringRequest(
            Request.Method.POST, uri.toString(),
            { response ->
                try {
                    val moveToMenu = Intent(this, MenuActivity::class.java)
                    startActivity(moveToMenu)
                    Toast.makeText(this, "Pesan Hotel Berhasil!", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, "Pesan Hotel Gagal!", Toast.LENGTH_LONG).show()
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