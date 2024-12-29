package com.ivantest.nooroweather.api

import com.ivantest.nooroweather.model.api.WeatherError

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val apiError: WeatherError?) : ApiResult<Nothing>()
}