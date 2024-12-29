package com.ivantest.nooroweather.data

import com.ivantest.nooroweather.BuildConfig
import com.ivantest.nooroweather.api.WeatherApiService
import com.ivantest.nooroweather.model.api.WeatherResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeather(city: String): Response<WeatherResponse> {
        return weatherApiService.getWeather(city = city, key = BuildConfig.WEATHER_API_KEY)
    }
}