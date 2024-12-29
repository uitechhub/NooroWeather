package com.ivantest.nooroweather.data

import com.ivantest.nooroweather.model.api.WeatherResponse
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeather(city: String): Response<WeatherResponse>
}