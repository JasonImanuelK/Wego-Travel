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
import com.example.wego_travel.Models.HistoryHotel
import com.example.wego_travel.Models.Pengguna
import com.google.gson.Gson
import okhttp3.MultipartBody
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val btnRegister: Button = findViewById(R.id.btn_nav_register)
        btnRegister.setOnClickListener(this)

        val btnLogin: Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.btn_nav_register ->{
                val navRegister = Intent(this, RegisterActivity::class.java)
                startActivity(navRegister)
            }
            R.id.btn_login -> {
                val edtEmail : EditText = findViewById(R.id.email)
                val edtPassword : EditText = findViewById(R.id.password)
                checkLogin(edtEmail.text.toString(), edtPassword.text.toString())
            }
        }
    }

    fun checkLogin(email: String, password: String) {
        val requestBody = "email="+email+"&password="+password

        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/Login").buildUpon()
            .build()
        val stringRequest = object : StringRequest(
            Request.Method.POST, uri.toString(),
            { response ->
                try {
                    val gson = Gson()
                    val a = gson.fromJson(response, Pengguna::class.java)
                    Pengguna.setInstance(a)
                    Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_LONG).show()
                    val login = Intent(this, MenuActivity::class.java)
                    startActivity(login)
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
