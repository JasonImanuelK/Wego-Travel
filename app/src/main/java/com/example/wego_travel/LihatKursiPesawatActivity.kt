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
import com.example.wego_travel.Models.KursiPesawat
import com.example.wego_travel.Models.Pengguna
import com.example.wego_travel.databinding.ActivityLihatKursiPesawatBinding
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class LihatKursiPesawatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLihatKursiPesawatBinding
    private val list = ArrayList<KursiPesawat>()
    private var title = "Krusi Pesawat"
    private val pengguna = Pengguna.getInstance()

    companion object {
        const val ID_PESAWAT = "id_pesawat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLihatKursiPesawatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvLihatKursiPesawat.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            getKursiPesawat()
        }
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun getKursiPesawat() {
        val id_pesawat = intent.getStringExtra(ID_PESAWAT)
        val listKursiPesawat: ArrayList<KursiPesawat> = ArrayList()
        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/Pesawat/Kursi/"+id_pesawat.toString()).buildUpon()
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
                        val a = gson.fromJson(raw.toString(), KursiPesawat::class.java)
                        listKursiPesawat.add(a)
                    }
                    list.addAll(listKursiPesawat)
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
        binding.rvLihatKursiPesawat.layoutManager = LinearLayoutManager(this)
        val listKursiPesawatAdapter = ListLihatKursiAdapter(list)
        binding.rvLihatKursiPesawat.adapter = listKursiPesawatAdapter
    }
}