package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

@Parcelize
data class Pengguna (
    @SerializedName("id_pengguna")
    var id_pengguna: Int,
    @SerializedName("nama")
    var nama:String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password:String,
    @SerializedName("jenis_kelamin")
    var jenis_kelamin:String,
    @SerializedName("tanggal_lahir")
    var tanggal_lahir:Date?,
    @SerializedName("nomor_telepon")
    var nomor_telepon:String,
    @SerializedName("alamat")
    var alamat:String
) : Parcelable {
    companion object {
        @Volatile
        private var instance: Pengguna? = null

        fun getInstance(): Pengguna = instance ?: synchronized(this) {
                instance ?: Pengguna(
                    0, "", "", "", "", Date(), "", ""
                )
        }

        fun setInstance(pengguna: Pengguna) {
            instance = pengguna
        }
    }
}