package com.example.wego_travel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.wego_travel.Models.Pengguna
import com.example.wego_travel.Models.Token

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    private val token = Token.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val imgAirplane: ImageView = findViewById(R.id.img_airplane)
        imgAirplane.setOnClickListener(this)

        val imgHotel: ImageView = findViewById(R.id.img_hotel)
        imgHotel.setOnClickListener(this)
        Log.v("Ini Token :", token.token)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.img_airplane ->{
                val navAirplane = Intent(this, FilterPesawatActivity::class.java)
                startActivity(navAirplane)
            }
            R.id.img_hotel -> {
                val navHotel = Intent(this, FilterHotelActivity::class.java)
                startActivity(navHotel)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        if (token.token==""){
            val menuItem = menu.findItem(R.id.lihat_history_hotel)
            val menuItem2 = menu.findItem(R.id.lihat_history_pesawat)
            val menuItem3 = menu.findItem(R.id.update_profile)
            val menuItem4 = menu.findItem(R.id.lihat_kupon)
            val menuItem5 = menu.findItem(R.id.logout)
            menuItem.setVisible(false)
            menuItem2.setVisible(false)
            menuItem3.setVisible(false)
            menuItem4.setVisible(false)
            menuItem5.setVisible(false)
        } else {
            val menuItem= menu.findItem(R.id.login_guest)
            menuItem.setVisible(false)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.update_profile -> {
                val navUpdateProfile = Intent(this, UpdateProfileActivity::class.java)
                startActivity(navUpdateProfile)
            }
            R.id.lihat_history_pesawat -> {
                val navHistoryPesawat = Intent(this, HistoryPesawatActivity::class.java)
                startActivity(navHistoryPesawat)
            }
            R.id.lihat_history_hotel -> {
                val navHistoryHotel = Intent(this, HistoryHotelActivity::class.java)
                startActivity(navHistoryHotel)
            }
            R.id.lihat_kupon -> {
                val navKupon = Intent(this, LihatKuponActivity::class.java)
                startActivity(navKupon)
            }
            R.id.login_guest -> {
                val navLoginGuest = Intent(this, MainActivity::class.java)
                startActivity(navLoginGuest)
            }
            R.id.logout -> {
                Token.setInstance(Token("", true))
                val navLogout = Intent(this, MainActivity::class.java)
                finishAffinity()
                startActivity(navLogout)
                Toast.makeText(this, "Berhasil Logout!", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}