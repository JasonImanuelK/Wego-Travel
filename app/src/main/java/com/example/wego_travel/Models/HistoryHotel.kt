package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class HistoryHotel (
    @SerializedName("nama_hotel")
    var nama_hotel: String,
    @SerializedName("id_tiket_hotel")
    var id_tiket_hotel: String,
    @SerializedName("tanggal_pemesanan")
    var tanggal_pemesanan:Date,
    @SerializedName("status_pemesanan")
    var status_pemesanan: String,
    @SerializedName("id_voucher")
    var id_voucher:Int?
) : Parcelable