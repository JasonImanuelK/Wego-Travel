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
import com.example.wego_travel.Models.Pesawat
import com.example.wego_travel.databinding.ActivityHistoryPesawatBinding
import com.example.wego_travel.databinding.ActivityPesanPesawatBinding
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class PesanPesawatActivity : AppCompatActivity() {
    private val list = ArrayList<Pesawat>()
    private lateinit var binding: ActivityPesanPesawatBinding
    private var title = "History Pesawat"
    companion object {
        const val ASAL = "asal"
        const val TUJUAN = "tujuan"
        const val TANGGAL = "tanggal"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesanPesawatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPesanPesawat.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            val asal = intent.getStringExtra(ASAL)
            val tujuan = intent.getStringExtra(TUJUAN)
            val tanggal = intent.getStringExtra(TANGGAL)
            cariPesawat(asal.toString() , tujuan.toString(), tanggal.toString())
        }
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
    fun cariPesawat(tempat_berangkat: String, tujuan_berangkat: String, tanggal_berangkat:String) {
        val listPesawat: ArrayList<Pesawat> = ArrayList()
        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/Pesawat/"+tempat_berangkat+"/"+tujuan_berangkat+"/"+tanggal_berangkat).buildUpon()
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
                        val a = gson.fromJson(raw.toString(), Pesawat::class.java)
                        listPesawat.add(a)
                        Log.v("Time : ", listPesawat.toString())
                    }
                    list.addAll(listPesawat)
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
        binding.rvPesanPesawat.layoutManager = LinearLayoutManager(this)
        val listPesawatAdapter = ListPesawatAdapter(list)
        binding.rvPesanPesawat.adapter = listPesawatAdapter
    }
}