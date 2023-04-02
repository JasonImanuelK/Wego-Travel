package com.example.wego_travel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

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
                val url = URL("localhost:8080/UpdateProfil/1")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "PUT"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.doOutput = true

                // Get the form data from the user
                val namaField = findViewById<EditText>(R.id.nama)
                val emailField = findViewById<EditText>(R.id.email)
                val alamatField = findViewById<EditText>(R.id.alamat)
                val noTelpField = findViewById<EditText>(R.id.no_telp)
                val tanggalLahirField = findViewById<EditText>(R.id.tanggal_lahir)
                val nama = namaField.text.toString()
                val email = emailField.text.toString()
                val alamat = alamatField.text.toString()
                val no_telp = noTelpField.text.toString()
                val tanggal_lahir = tanggalLahirField.text.toString()

                // Create a JSON object with the form data
                val data = JSONObject()
                data.put("nama", nama)
                data.put("email", email)
                data.put("alamat", alamat)
                data.put("nomor_telepon", no_telp)
                data.put("tanggal_lahir", tanggal_lahir)

                // Write the JSON data to the request body
                val outputStream = connection.outputStream
                val writer = BufferedWriter(OutputStreamWriter(outputStream))
                writer.write(data.toString())
                writer.flush()
                writer.close()
                outputStream.close()

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    val response = inputStream.bufferedReader().use { it.readText() }
                    // TODO: handle successful response
                } else {
                    // TODO: handle error response
                }
                connection.disconnect()
            }
        }
    }
}