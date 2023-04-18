package com.example.wego_travel

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.text.SimpleDateFormat

class TampilanHistoryPesawatActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val ID_TIKET_PESAWAT = "id_tiket_pesawat"
        const val MASKAPAI = "maskapai"
        const val TANGAL_PEMESANAN = "tanggal_pemesanan"
        const val STATUS_PEMESANAN = "status_pemesanan"
        const val ID_VOUCHER = "id_voucher"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampilan_history_pesawat)

        val btnRefundPesawat: Button = findViewById(R.id.refund_pesawat)
        btnRefundPesawat.setOnClickListener(this)

        val btnQrPesawat: Button = findViewById(R.id.qr_pesawat)
        btnQrPesawat.setOnClickListener(this)

        val id_tiket_pesawat = intent.getStringExtra(ID_TIKET_PESAWAT)
        val tujuan = intent.getStringExtra(MASKAPAI)
        val tanggal_pemesanan = intent.getStringExtra(TANGAL_PEMESANAN)
        val status_pemesanan = intent.getStringExtra(STATUS_PEMESANAN)
        val id_voucher = intent.getStringExtra(ID_VOUCHER)
        val labelIdTiketPesawat : TextView = findViewById(R.id.label_id_tiket_pesawat)
        val labelMaskapai : TextView = findViewById(R.id.label_maskapai)
        val labelTanggalPemesanan : TextView = findViewById(R.id.label_tanggal_pemesanan_pesawat)
        val labelStatusPemesanan : TextView = findViewById(R.id.label_status_pemesanan)
        val labelIdVoucher : TextView = findViewById(R.id.label_id_voucher_pesawat)
        labelIdTiketPesawat.setText(id_tiket_pesawat)
        labelMaskapai.setText(tujuan)
        labelTanggalPemesanan.setText(tanggal_pemesanan)
        labelStatusPemesanan.setText(status_pemesanan)
        labelIdVoucher.setText(id_voucher)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.refund_pesawat ->{
                val id_tiket_pesawat = intent.getStringExtra(ID_TIKET_PESAWAT)
                refund(id_tiket_pesawat.toString())
            }
            R.id.qr_pesawat ->{
                Toast.makeText(this, "INI QR!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun refund(id_tiket_pesawat:String) {
        var requestBody = "id_tiket_pesawat="+id_tiket_pesawat
        Log.v("ID PESAWAT NIH : ", id_tiket_pesawat.toString())

        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/BatalPesanPesawat").buildUpon()
            .build()
        val stringRequest = object : StringRequest(
            Request.Method.PUT, uri.toString(),
            { response ->
                try {
                    Toast.makeText(this, "Refund Berhasil!", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, "Refund Gagal!", Toast.LENGTH_LONG).show()
                error.printStackTrace()
            }) {
            @Throws
            override fun getBodyContentType(): String {
                // Set the content type to x-www-form-urlencoded
                return "application/x-www-form-urlencoded; charset=UTF-8"
            }

            override fun getBody(): ByteArray {
                // Convert the request body to a byte array
                return requestBody.toByteArray(Charsets.UTF_8)
            }
        }
        requestQueue.add(stringRequest)
    }
}