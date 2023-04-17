package com.example.wego_travel.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Token (
    @SerializedName("token")
    var token: String
) : Parcelable