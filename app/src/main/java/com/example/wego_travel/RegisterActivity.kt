package com.example.wego_travel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.Pengguna
import com.google.gson.Gson
import org.json.JSONException
import java.util.Date

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_page)

        val btnRegister: Button = findViewById(R.id.btn_register)
        btnRegister.setOnClickListener(this)

        val btnBatalRegister: Button = findViewById(R.id.btn_batal_register)
        btnBatalRegister.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.btn_batal_register ->{
                val navRegister = Intent(this, MainActivity::class.java)
                startActivity(navRegister)
            }
            R.id.btn_register -> {
                val edtNama : EditText = findViewById(R.id.nama)
                val edtEmail : EditText = findViewById(R.id.email)
                val edtPassword : EditText = findViewById(R.id.password)
                val radioGroupJenisKelamin = findViewById<RadioGroup>(R.id.gender_radio_group)
                val selectedRadioButtonId = radioGroupJenisKelamin.checkedRadioButtonId
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedRadioButtonText = selectedRadioButton.text.toString()
                val edtTanggalLahir : EditText = findViewById(R.id.TanggalLahir)
                val edtNomorTelepon : EditText = findViewById(R.id.NoTelepon)
                val edtAlamat : EditText = findViewById(R.id.Alamat)
                val edtRetypePassword : EditText = findViewById(R.id.ReTypePassword)
                if (edtPassword.text.toString()==edtRetypePassword.text.toString()){
                    Log.v("Tanggal: ", edtTanggalLahir.text.toString())
                    createRegister(edtNama.text.toString(), edtEmail.text.toString(), edtPassword.text.toString(), selectedRadioButtonText, edtTanggalLahir.text.toString(), edtNomorTelepon.text.toString(), edtAlamat.text.toString())
                } else {
                    Toast.makeText(this, "Passwordmu Tidak Sama!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun createRegister(nama: String, email: String, password: String, jenis_kelamin: String, tanggal_lahir:String, nomor_telepon:String, alamat: String) {
        val requestBody = "nama="+nama+"&email="+email+"&password="+password+"&jenis_kelamin="+jenis_kelamin+"&tanggal_lahir="+tanggal_lahir+"&nomor_telepon="+nomor_telepon+"&alamat="+alamat

        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://172.20.10.9:8080/Register").buildUpon()
            .build()
        val stringRequest = object : StringRequest(
            Request.Method.POST, uri.toString(),
            { response ->
                try {
                    val login = Intent(this, MainActivity::class.java)
                    startActivity(login)
                    Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, "Registrasi Gagal!", Toast.LENGTH_LONG).show()
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