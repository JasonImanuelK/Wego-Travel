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

        val btnMoveActivity: Button = findViewById(R.id.btn_register)
        btnMoveActivity.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.btn_register ->{
                Log.v("Sini","SINI")
                val moveBack = Intent(this@MainActivity, UpdateProfileActivity::class.java)
                startActivity(moveBack)
            }
        }
    }
}