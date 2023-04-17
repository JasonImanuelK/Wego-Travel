package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.sql.Time
import java.util.Date

@Parcelize
data class Pesawat (
    @SerializedName("id_pesawat")
    var id_pesawat: Int,
    @SerializedName("maskapai")
    var maskapai: String,
    @SerializedName("tempat_berangkat")
    var tempat_berangkat: String,
    @SerializedName("tujuan_berangkat")
    var tujuan_berangkat: String,
    @SerializedName("tanggal_berangkat")
    var tanggal_berangkat:Date,
    @SerializedName("jam_berangkat")
    var jam_berangkat:String,
    @SerializedName("promo")
    var promo:Double
) : Parcelable