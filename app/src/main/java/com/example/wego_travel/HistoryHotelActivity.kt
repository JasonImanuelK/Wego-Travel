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
import com.example.wego_travel.Models.HistoryHotel
import com.example.wego_travel.databinding.ActivityHistoryHotelBinding
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class HistoryHotelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryHotelBinding
    private val list = ArrayList<HistoryHotel>()
    private var title = "Mode List"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHistoryHotel.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            getHistoryHotel()
            Log.v("HASIL SEBENARNYA : ", list.toString())
        }
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun getHistoryHotel(): ArrayList<HistoryHotel> {
        val listHistoryHotel: ArrayList<HistoryHotel> = ArrayList()
        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/LihatHistoryHotel/1").buildUpon()
            .build()
        Log.v("Ini 1", "LOH")
        val stringRequest = object : StringRequest(
            Request.Method.GET, uri.toString(),
            { response ->
                Log.v("Ini 2", "LOH")
                var obj: JSONObject? = null
                try {
                    obj = JSONObject(response)
                    val jsonArray = obj.getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        val raw = jsonArray.getJSONObject(i)
                        val gson = Gson()
                        val a = gson.fromJson(raw.toString(), HistoryHotel::class.java)
                        listHistoryHotel.add(a)
                        Log.v("Hasil : ", listHistoryHotel.toString())
                    }
                    list.addAll(listHistoryHotel)
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
                Log.v("Ini 3", "LOH")
                val headers: MutableMap<String, String> = HashMap()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }
        Log.v("Ini 4", "LOH")
        requestQueue.add(stringRequest)
        Log.v("Lah : ", listHistoryHotel.toString())
        return listHistoryHotel
    }

    private fun showRecyclerList() {
        binding.rvHistoryHotel.layoutManager = LinearLayoutManager(this)
        val listHistoryHotelAdapter = ListHistoryHotelAdapter(list)
        binding.rvHistoryHotel.adapter = listHistoryHotelAdapter
    }
}