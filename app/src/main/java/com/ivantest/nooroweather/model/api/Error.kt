package com.ivantest.nooroweather.model.api

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("message")
    val message: String? = null
)