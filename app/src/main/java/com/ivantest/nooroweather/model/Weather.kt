package com.ivantest.nooroweather.model

data class Weather(
    val city: String = "",
    val temperature: Int = 0,
    val iconUrl: String = "",
    val humidity: Int = 0,
    val uvIndex: Int = 0,
    val feelsLike: Int = 0,
)
