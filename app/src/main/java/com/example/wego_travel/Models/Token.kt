package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Token (
    var token: String,
    var check: Boolean
) : Parcelable {
    companion object {
        @Volatile
        private var instance: Token? = null

        fun getInstance(): Token = instance ?: synchronized(this) {
            instance ?: Token(
                "", false
            )
        }
        fun setInstance(token: Token) {
            instance = token
        }
    }
}