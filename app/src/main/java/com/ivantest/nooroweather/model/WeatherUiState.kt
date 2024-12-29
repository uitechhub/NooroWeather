package com.ivantest.nooroweather.model

data class WeatherUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val showDetails: Boolean = false,
    val weatherDetails: Weather? = null,
    val error: String? = null
)
