package com.ivantest.nooroweather.ui.screens.weather

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ivantest.nooroweather.R
import com.ivantest.nooroweather.model.Weather
import com.ivantest.nooroweather.ui.components.EmptyView
import com.ivantest.nooroweather.ui.components.LoadingIndicator
import com.ivantest.nooroweather.ui.components.SearchLocationBar
import com.ivantest.nooroweather.ui.components.WeatherDetail
import com.ivantest.nooroweather.ui.components.WeatherResultCard
import kotlinx.coroutines.launch

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel
) {
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchLocationBar(
            query = state.query,
            onQueryChanged = { viewModel.onTextFieldValueChange(it) },
            onSearch = { scope.launch { viewModel.fetchWeather(state.query) } }
        )
        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.error != null -> Text("Error: ${state.error}")
            state.isLoading -> LoadingIndicator()
            state.weatherDetails != null -> {
                Crossfade(targetState = state.weatherDetails, label = "weather_results_details_animation") { weather ->
                    if (!state.showDetails) {
                        WeatherResultCard(weather!!) { viewModel.onResultCardClicked() }
                    } else {
                        WeatherDetails(weather!!)
                    }

                }
            }
            else -> EmptyView()
        }
    }
}

@Composable
fun WeatherDetails(weather: Weather) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherDetail(
            cityName = weather.city,
            iconURL = weather.iconUrl,
            uv = "${weather.uvIndex}",
            feelsLike = stringResource(R.string.temp_with_degrees_placeholder, weather.feelsLike),
            humidity = stringResource(R.string.percent, weather.humidity),
            temperature = "${weather.temperature}"
        )
    }
}