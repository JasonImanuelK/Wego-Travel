package com.example.wego_travel

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class TampilanHistoryHotelActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val ID_TIKET_HOTEL = "id_tiket_hotel"
        const val NAMA_HOTEL = "nama_hotel"
        const val TANGAL_PEMESANAN = "tanggal_pemesanan"
        const val STATUS_PEMESANAN = "status_pemesanan"
        const val ID_VOUCHER = "id_voucher"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampilan_history_hotel)

        val btnRefundPesawat: Button = findViewById(R.id.refund_hotel)
        btnRefundPesawat.setOnClickListener(this)

        val btnQrPesawat: Button = findViewById(R.id.qr_hotel)
        btnQrPesawat.setOnClickListener(this)

        val id_tiket_pesawat = intent.getStringExtra(ID_TIKET_HOTEL)
        val tujuan = intent.getStringExtra(NAMA_HOTEL)
        val tanggal_pemesanan = intent.getStringExtra(TANGAL_PEMESANAN)
        val status_pemesanan = intent.getStringExtra(STATUS_PEMESANAN)
        val id_voucher = intent.getStringExtra(ID_VOUCHER)
        val labelIdTiketHotel : TextView = findViewById(R.id.label_id_hotel)
        val labelNamaHotel : TextView = findViewById(R.id.label_nama_hotel)
        val labelTanggalPemesanan : TextView = findViewById(R.id.label_tanggal_pemesanan_hotel)
        val labelStatusPemesanan : TextView = findViewById(R.id.label_status_pemesanan_hotel)
        val labelIdVoucher : TextView = findViewById(R.id.label_id_voucher_Hotel)
        labelIdTiketHotel.setText(id_tiket_pesawat)
        labelNamaHotel.setText(tujuan)
        labelTanggalPemesanan.setText(tanggal_pemesanan)
        labelStatusPemesanan.setText(status_pemesanan)
        labelIdVoucher.setText(id_voucher)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.refund_hotel ->{
                val id_tiket_hotel = intent.getStringExtra(ID_TIKET_HOTEL)
                refund(id_tiket_hotel.toString())
            }
            R.id.qr_hotel ->{
                Toast.makeText(this, "INI QR!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun refund(id_tiket_hotel:String) {
        var requestBody = "id_tiket_hotel="+id_tiket_hotel

        val requestQueue = Volley.newRequestQueue(this)
        val uri = Uri.parse("http://192.168.100.31:8080/BatalPesanHotel").buildUpon()
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