package com.example.wego_travel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

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
                val login = Intent(this, MenuActivity::class.java)
                startActivity(login)
            }
        }
    }
}