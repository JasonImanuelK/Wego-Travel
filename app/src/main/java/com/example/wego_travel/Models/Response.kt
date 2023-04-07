package com.example.wego_travel.Models

import com.google.gson.annotations.SerializedName

class Response {
    @SerializedName("Status")
    var status: Int = 0
    get() = field
    set(value){
        field = value
    }
    @SerializedName("Message")
    var message: String? = null
        get() = field
        set(value){
            field = value
        }
}
