package com.ivantest.nooroweather.model.api

import com.google.gson.annotations.SerializedName

data class AirQuality(
    @SerializedName("co")
    val co: Double? = null,
    @SerializedName("gb-defra-index")
    val gbDefraIndex: Int? = null,
    @SerializedName("no2")
    val no2: Double? = null,
    @SerializedName("o3")
    val o3: Double? = null,
    @SerializedName("pm10")
    val pm10: Int? = null,
    @SerializedName("pm2_5")
    val pm25: Double? = null,
    @SerializedName("so2")
    val so2: Int? = null,
    @SerializedName("us-epa-index")
    val usEpaIndex: Int? = null
)