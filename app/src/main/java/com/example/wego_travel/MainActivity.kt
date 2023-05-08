package com.example.wego_travel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.CookieManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.Pengguna
import com.example.wego_travel.Models.Token
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpCookie
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val token = Token.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (token.check==false){
            Token.setInstance(Token("", true))
            val moveWithToken = Intent(this, MenuActivity::class.java)
            startActivity(moveWithToken)
        } else {
            setContentView(R.layout.login_page)
            val btnRegister: Button = findViewById(R.id.btn_nav_register)
            btnRegister.setOnClickListener(this)

            val btnLogin: Button = findViewById(R.id.btn_login)
            btnLogin.setOnClickListener(this)

            val btnLoginAsGuest: Button = findViewById(R.id.btn_login_as_guest)
            btnLoginAsGuest.setOnClickListener(this)

            val inputStream = assets.open("email.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val emails = mutableListOf<String>()
            var line: String? = reader.readLine()
            while (line != null) {
                emails.add(line)
                line = reader.readLine()
            }
            reader.close()

            val arrayAdapter = MutableListArrayAdapter(this, emails)
            val email : AutoCompleteTextView = findViewById(R.id.email)
            email.setAdapter(arrayAdapter)
            Log.v("Nama-Nama Email ", emails.toString())
        }
        Log.v("Ini Token :", token.token)
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
            R.id.btn_login_as_guest -> {
                val navLoginAsGuest = Intent(this, MenuActivity::class.java)
                startActivity(navLoginAsGuest)
            }
        }
    }

    fun checkLogin(email: String, password: String) {
        val requestBody = "email="+email+"&password="+password

        val requestQueue = Volley.newRequestQueue(this)
        val url = "http://"+getString(R.string.ip_address)+":8080/Login"
        val uri = Uri.parse(url).buildUpon()
            .build()
        val stringRequest = object : StringRequest(
            Request.Method.POST, uri.toString(),
            { response ->
                try {
                    val gson = Gson()
                    val jsonObject = JSONObject(response)
                    val jsonTokenObject = JSONObject(response)
                    val dataObject = jsonObject.getJSONObject("data")
                    val token = jsonTokenObject.getString("token")
                    val a = gson.fromJson(dataObject.toString(), Pengguna::class.java)
                    Pengguna.setInstance(a)
                    val getToken = Token(token, true)
                    Token.setInstance(getToken)
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
