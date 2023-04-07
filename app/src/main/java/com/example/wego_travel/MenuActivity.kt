package com.example.wego_travel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val imgAirplane: ImageView = findViewById(R.id.img_airplane)
        imgAirplane.setOnClickListener(this)

        val imgHotel: ImageView = findViewById(R.id.img_hotel)
        imgHotel.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.img_airplane ->{
                val navAirplane = Intent(this, MainActivity::class.java)
                startActivity(navAirplane)
            }
            R.id.img_hotel -> {
                val navHotel = Intent(this, MainActivity::class.java)
                startActivity(navHotel)
            }
        }
    }
}