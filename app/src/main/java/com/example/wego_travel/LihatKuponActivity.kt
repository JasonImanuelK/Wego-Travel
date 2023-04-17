package com.example.wego_travel

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.Kupon
import com.example.wego_travel.Models.Pengguna
import com.example.wego_travel.databinding.ActivityLihatKuponBinding
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class LihatKuponActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLihatKuponBinding
    private val list = ArrayList<Kupon>()
    private var title = "Kupon"
    private val pengguna = Pengguna.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLihatKuponBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvLihatKupon.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            getLihatKupon()
        }
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun getLihatKupon() {
        val listKupon: ArrayList<Kupon> = ArrayList()
        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/LihatKupon/"+pengguna.id_pengguna.toString()).buildUpon()
            .build()
        Log.v("Id Pengguna : ", pengguna.id_pengguna.toString())
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
                        val a = gson.fromJson(raw.toString(), Kupon::class.java)
                        listKupon.add(a)
                        Log.v("Hasil : ", listKupon.toString())
                    }
                    list.addAll(listKupon)
                    showRecyclerGrid()
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

    private fun showRecyclerGrid() {
        binding.rvLihatKupon.layoutManager = GridLayoutManager(this, 2)
        val gridKuponAdapter = GridKuponAdapter(list)
        binding.rvLihatKupon.adapter = gridKuponAdapter
    }
}