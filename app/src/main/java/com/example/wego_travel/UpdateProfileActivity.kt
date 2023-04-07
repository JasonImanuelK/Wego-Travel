package com.example.wego_travel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wego_travel.Models.Article
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class UpdateProfileActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_profile_page)

        val btnBatal: Button = findViewById(R.id.btn_batal)
        btnBatal.setOnClickListener(this)

        val btnSimpan: Button = findViewById(R.id.btn_simpan)
        btnSimpan.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.btn_batal ->{
                val back = Intent(this, MainActivity::class.java)
                startActivity(back)
            }
            R.id.btn_simpan ->{
                val articles: ArrayList<Article> = ArrayList()
                val requestQueue = Volley.newRequestQueue(this)
                val uri = Uri.parse("https://jsonplaceholder.typicode.com/posts/1").buildUpon()
                    .build()
                val stringRequest = object : StringRequest(
                    Request.Method.GET, uri.toString(),
                    { response ->
                        var obj: JSONObject? = null
                        try {
                            obj = JSONObject(response)
                            val jsonArray = obj.getJSONArray("articles")
                            for (i in 0 until jsonArray.length()) {
                                val raw = jsonArray.getJSONObject(i)
                                val gson = Gson()
                                val a = gson.fromJson(raw.toString(), Article::class.java)
                                articles.add(a)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    { error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                        error.printStackTrace()
                    }) {
                        @Throws(AuthFailureError::class)
                        override fun getHeaders(): Map<String, String> {
                            val headers: MutableMap<String, String> = HashMap()
                            headers["User-Agent"] = "Mozilla/5.0"
                            return headers
                        }
                    }
                requestQueue.add(stringRequest)
                if(articles.isNotEmpty()) {
                    Log.v("Hasil : ", "true")
                }else{
                    Log.v("Hasil : ", "false")
                }
            }
        }
    }
}