package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
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
    @SerializedName("Jenis_kelamin")
    var Jenis_kelamin:String,
    @SerializedName("Tanggal_lahir")
    var Tanggal_lahir:Date?,
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