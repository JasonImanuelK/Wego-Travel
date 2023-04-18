package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class KursiPesawat (
    @SerializedName("nomor_kursi")
    var nomor_kursi: String,
    @SerializedName("tipe_kursi")
    var tipe_kursi: String,
    @SerializedName("harga_kursi")
    var harga_kursi: Double,
    @SerializedName("status_kursi")
    var status_kursi: String,
    @SerializedName("id_pesawat")
    var id_pesawat:Int
) : Parcelable