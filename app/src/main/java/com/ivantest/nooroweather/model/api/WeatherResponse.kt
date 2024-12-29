package com.ivantest.nooroweather.model.api

import com.google.gson.annotations.SerializedName
import com.ivantest.nooroweather.api.WeatherApiService
import com.ivantest.nooroweather.model.Weather
import kotlin.math.roundToInt

data class WeatherResponse(
    @SerializedName("current")
    val current: Current? = null,
    @SerializedName("location")
    val location: Location? = null,
    @SerializedName("error")
    val error: WeatherError? = null
) {

    fun toWeather(): Weather {
        return Weather(
            city = location?.name ?: "",
            temperature = current?.tempC?.roundToInt() ?: 0,
            humidity = current?.humidity?.roundToInt() ?: 0,
            uvIndex = current?.uv?.roundToInt() ?: 0,
            feelsLike = current?.feelslikeC?.roundToInt() ?: 0,
            iconUrl = "${WeatherApiService.HTTPS_PROTOCOL}${current?.condition?.icon}"
        )
    }
}