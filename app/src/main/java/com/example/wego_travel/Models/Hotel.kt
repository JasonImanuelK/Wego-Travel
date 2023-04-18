package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Hotel (
    @SerializedName("id_hotel")
    var id_hotel: Int,
    @SerializedName("nama_hotel")
    var nama_hotel: String,
    @SerializedName("alamat_hotel")
    var alamat_hotel: String,
    @SerializedName("deskripsi")
    var deskripsi: String,
    @SerializedName("rating")
    var rating: Int,
    @SerializedName("promo")
    var promo:Double
) : Parcelable