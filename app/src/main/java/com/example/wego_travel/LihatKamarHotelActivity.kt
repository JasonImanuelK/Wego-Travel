package com.example.wego_travel

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.KamarHotel
import com.example.wego_travel.Models.KursiPesawat
import com.example.wego_travel.Models.Pengguna
import com.example.wego_travel.databinding.ActivityLihatKamarHotelBinding
import com.example.wego_travel.databinding.ActivityLihatKursiPesawatBinding
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class LihatKamarHotelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLihatKamarHotelBinding
    private val list = ArrayList<KamarHotel>()
    private var title = "Kamar Hotel"

    companion object {
        const val ID_HOTEL = "id_hotel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLihatKamarHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvLihatKamarHotel.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            getKursiPesawat()
        }
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun getKursiPesawat() {
        val id_hotel = intent.getStringExtra(ID_HOTEL)
        val listKamarHotel: ArrayList<KamarHotel> = ArrayList()
        val requestQueue = Volley.newRequestQueue(this)
        val url = "http://"+getString(R.string.ip_address)+":8080/Hotel/Kamar/"+id_hotel.toString()
        val uri = Uri.parse(url).buildUpon()
            .build()
        val stringRequest = object : StringRequest(
            Request.Method.GET, uri.toString(),
            { response ->
                var obj: JSONObject? = null
                try {
                    obj = JSONObject(response)
                    val jsonArray = obj.getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        val raw = jsonArray.getJSONObject(i)
                        val gson = Gson()
                        val a = gson.fromJson(raw.toString(), KamarHotel::class.java)
                        listKamarHotel.add(a)
                    }
                    list.addAll(listKamarHotel)
                    showRecyclerList()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                error.printStackTrace()
            }) {
            @Throws
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }
        requestQueue.add(stringRequest)
    }

    private fun showRecyclerList() {
        binding.rvLihatKamarHotel.layoutManager = LinearLayoutManager(this)
        val listLihatKamarAdapter = ListLihatKamarHotelAdapter(list)
        binding.rvLihatKamarHotel.adapter = listLihatKamarAdapter
    }
}