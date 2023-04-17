package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Kupon (
    @SerializedName("id_voucher")
    var id_voucher: Int,
    @SerializedName("nama_voucher")
    var nama_voucher: String,
    @SerializedName("nilai")
    var nilai: Double,
    @SerializedName("tipe_tiket")
    var tipe_tiket: String,
    @SerializedName("status_penggunaan")
    var status_penggunaan:String
) : Parcelable