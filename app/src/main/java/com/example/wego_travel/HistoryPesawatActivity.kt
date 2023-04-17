package com.example.wego_travel

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.HistoryPesawat
import com.example.wego_travel.Models.Pengguna
import com.example.wego_travel.databinding.ActivityHistoryPesawatBinding
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class HistoryPesawatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryPesawatBinding
    private val list = ArrayList<HistoryPesawat>()
    private var title = "History Pesawat"
    private val pengguna = Pengguna.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryPesawatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHistoryPesawat.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            getHistoryPesawat()
        }
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun getHistoryPesawat() {
        val listHistoryPesawat: ArrayList<HistoryPesawat> = ArrayList()
        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://172.20.10.9:8080/LihatHistoryPesawat/"+pengguna.id_pengguna.toString()).buildUpon()
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
                        val a = gson.fromJson(raw.toString(), HistoryPesawat::class.java)
                        listHistoryPesawat.add(a)
                    }
                    list.addAll(listHistoryPesawat)
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
        binding.rvHistoryPesawat.layoutManager = LinearLayoutManager(this)
        val listHistoryPesawawatAdapter = ListHistoryPesawatAdapter(list)
        binding.rvHistoryPesawat.adapter = listHistoryPesawawatAdapter
    }
}