package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class HistoryPesawat (
    @SerializedName("maskapai")
    var maskapai: String,
    @SerializedName("id_tiket_pesawat")
    var id_tiket_pesawat: Int,
    @SerializedName("tanggal_pemesanan")
    var tanggal_pemesanan: Date,
    @SerializedName("status_pemesanan")
    var status_pemesanan: String,
    @SerializedName("id_voucher")
    var id_voucher:Int?
) : Parcelable