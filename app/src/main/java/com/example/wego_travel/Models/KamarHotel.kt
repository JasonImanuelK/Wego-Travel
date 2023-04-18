package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KamarHotel (
    @SerializedName("nomor_kamar")
    var nomor_kamar: String,
    @SerializedName("tipe_kamar")
    var tipe_kamar: String,
    @SerializedName("harga_kamar")
    var harga_kamar: Double,
    @SerializedName("status_kamar")
    var status_kamar: String,
    @SerializedName("id_hotel")
    var id_hotel:Int
) : Parcelable