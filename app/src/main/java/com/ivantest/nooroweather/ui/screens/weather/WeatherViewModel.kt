package com.ivantest.nooroweather.ui.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivantest.nooroweather.api.ApiResult
import com.ivantest.nooroweather.data.WeatherRepository
import com.ivantest.nooroweather.model.WeatherUiState
import com.ivantest.nooroweather.store.DataStoreManager
import com.ivantest.nooroweather.util.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState

    init {
        viewModelScope.launch {
            dataStoreManager.lastSearchedCity.collect { query ->
                if (query != null) {
                    _uiState.update { state -> state.copy(query = query) }
                    if (!_uiState.value.showDetails) {
                        fetchWeather(query)
                    }
                }
            }
        }
    }

    fun fetchWeather(query: String) {
        if (_uiState.value.isLoading) {
            return
        }

        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true, error = null) }
            when (val response = safeApiCall { weatherRepository.getWeather(query) }) {
                is ApiResult.Success -> {
                    val weather = response.data.toWeather()
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            showDetails = false,
                            weatherDetails = weather,
                            error = null
                        )
                    }
                }

                is ApiResult.Error -> {
                    println(response.apiError)
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            weatherDetails = null,
                            error = response.apiError?.weatherError?.message
                        )
                    }
                }
            }
        }
    }

    fun onTextFieldValueChange(newValue: String) {
        _uiState.update { state -> state.copy(query = newValue) }
    }

    fun onResultCardClicked() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(showDetails = true) }
            dataStoreManager.saveLastSearchedCity(_uiState.value.weatherDetails?.city ?: _uiState.value.query)
        }
    }
}