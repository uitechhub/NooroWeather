package com.ivantest.nooroweather.api

import com.ivantest.nooroweather.model.api.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("q") city: String,
    ): Response<WeatherResponse>

    companion object {
        const val HTTPS_PROTOCOL = "https:"
        const val BASE_URL = "https://api.weatherapi.com/v1/"
    }
}