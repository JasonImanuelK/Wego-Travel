package com.example.wego_travel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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
                val login = Intent(this, UpdateProfileActivity::class.java)
                startActivity(login)
            }
        }
    }
}