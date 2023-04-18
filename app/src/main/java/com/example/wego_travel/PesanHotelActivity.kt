package com.example.wego_travel

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.Hotel
import com.example.wego_travel.Models.Pesawat
import com.example.wego_travel.databinding.ActivityPesanHotelBinding
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class PesanHotelActivity : AppCompatActivity() {
    private val list = ArrayList<Hotel>()
    private lateinit var binding: ActivityPesanHotelBinding
    private var title = "List Hotel"
    companion object {
        const val KOTA = "kota"
        const val TANGGAL_INAP = "tanggal_inap"
        const val AKHIR_INAP = "akhir_inap"
        const val JUMLAH_PENGINAP = "jumlah_penginap"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesanHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPesanHotel.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            val kota = intent.getStringExtra(KOTA)
            val tanggal_inap = intent.getStringExtra(TANGGAL_INAP)
            val akhir_inap = intent.getStringExtra(AKHIR_INAP)
            val jumlah_penginap = intent.getStringExtra(JUMLAH_PENGINAP)
            cariPesawat(kota.toString() , tanggal_inap.toString(), akhir_inap.toString(), jumlah_penginap.toString())
        }
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
    fun cariPesawat(kota: String, tanggal_inap: String, akhir_inap:String, jumlah_penginap:String) {
        val listHotel: ArrayList<Hotel> = ArrayList()
        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/Hotel/"+kota+"/"+tanggal_inap+"/"+akhir_inap+"/"+jumlah_penginap).buildUpon()
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
                        val a = gson.fromJson(raw.toString(), Hotel::class.java)
                        listHotel.add(a)
                        Log.v("Time : ", listHotel.toString())
                    }
                    list.addAll(listHotel)
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
        binding.rvPesanHotel.layoutManager = LinearLayoutManager(this)
        val listHotelAdapter = ListHotelAdapter(list)
        binding.rvPesanHotel.adapter = listHotelAdapter
    }
}