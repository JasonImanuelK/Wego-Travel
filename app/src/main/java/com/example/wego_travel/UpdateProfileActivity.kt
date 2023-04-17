package com.example.wego_travel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.Pengguna
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*

class UpdateProfileActivity : AppCompatActivity(), View.OnClickListener {
    private val pengguna = Pengguna.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_profile_page)

        val btnBatal: Button = findViewById(R.id.btn_batal_update)
        btnBatal.setOnClickListener(this)

        val btnSimpan: Button = findViewById(R.id.btn_simpan_update)
        btnSimpan.setOnClickListener(this)

        val edtNama : EditText = findViewById(R.id.update_nama)
        val edtEmail : EditText = findViewById(R.id.update_email)
        val edtTanggalLahir : EditText = findViewById(R.id.update_tanggalLahir)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val edtNomorTelepon : EditText = findViewById(R.id.update_no_telp)
        val edtAlamat : EditText = findViewById(R.id.update_alamat)
        edtNama.setText(pengguna.nama)
        edtEmail.setText(pengguna.email)
        edtTanggalLahir.setText(sdf.format(pengguna.tanggal_lahir))
        edtNomorTelepon.setText(pengguna.nomor_telepon)
        edtAlamat.setText(pengguna.alamat)
        val selectedRadioButton : RadioButton
        if (pengguna.jenis_kelamin=="Pria"){
            selectedRadioButton = findViewById(R.id.update_radio_button_pria)
            selectedRadioButton.setChecked(true)
        } else if (pengguna.jenis_kelamin=="Wanita"){
            selectedRadioButton = findViewById(R.id.update_radio_button_wanita)
            selectedRadioButton.setChecked(true)
        }
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.btn_batal_update ->{
                val back = Intent(this, MenuActivity::class.java)
                startActivity(back)
            }
            R.id.btn_simpan_update ->{
                val edtNama : EditText = findViewById(R.id.update_nama)
                val edtEmail : EditText = findViewById(R.id.update_email)
                val radioGroupJenisKelamin = findViewById<RadioGroup>(R.id.update_gender_radio_group)
                val selectedRadioButtonId = radioGroupJenisKelamin.checkedRadioButtonId
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedRadioButtonText = selectedRadioButton.text.toString()
                val edtTanggalLahir : EditText = findViewById(R.id.update_tanggalLahir)
                val edtNomorTelepon : EditText = findViewById(R.id.update_no_telp)
                val edtAlamat : EditText = findViewById(R.id.update_alamat)
                updateProfile(edtNama.text.toString(), edtEmail.text.toString(), selectedRadioButtonText, edtTanggalLahir.text.toString(), edtNomorTelepon.text.toString(), edtAlamat.text.toString())
            }
        }
    }

    fun updateProfile(nama: String, email: String, jenis_kelamin: String, tanggal_lahir:String, nomor_telepon:String, alamat: String) {
        var requestBody = ""
        if (nama!=pengguna.nama){
            requestBody += "&nama="+nama
            pengguna.nama = nama
        }
        if (email!=pengguna.email){
            requestBody += "&email="+email
            pengguna.email = email
        }
        if (jenis_kelamin!=pengguna.jenis_kelamin){
            requestBody += "&email="+jenis_kelamin
            pengguna.jenis_kelamin = jenis_kelamin
        }
        if (tanggal_lahir!=pengguna.tanggal_lahir.toString()){
            requestBody += "&tanggal_lahir="+tanggal_lahir
            pengguna.tanggal_lahir = stringToDate(tanggal_lahir, "yyyy-MM-dd")
        }
        if (nomor_telepon!=pengguna.nomor_telepon){
            requestBody += "&nomor_telepon="+nomor_telepon
            pengguna.nomor_telepon = nomor_telepon
        }
        if (alamat!=pengguna.alamat){
            requestBody += "&alamat="+alamat
            pengguna.alamat = alamat
        }
        requestBody.substring(1)

        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/UpdateProfil/"+pengguna.id_pengguna.toString()).buildUpon()
            .build()
        val stringRequest = object : StringRequest(
            Request.Method.PUT, uri.toString(),
            { response ->
                try {
                    Toast.makeText(this, "Update Berhasil!", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, "Update Gagal!", Toast.LENGTH_LONG).show()
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

    fun stringToDate(dateString: String, dateFormat: String): Date? {
        val format = SimpleDateFormat(dateFormat, Locale.getDefault())
        return try {
            format.parse(dateString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}