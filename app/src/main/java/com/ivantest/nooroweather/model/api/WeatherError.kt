package com.ivantest.nooroweather.model.api

import com.google.gson.annotations.SerializedName

data class WeatherError(
    @SerializedName("error")
    val weatherError: Error? = null
)