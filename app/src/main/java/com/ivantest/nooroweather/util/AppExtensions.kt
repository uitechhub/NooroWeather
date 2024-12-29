package com.ivantest.nooroweather.util

import android.util.Log
import com.google.gson.Gson
import com.ivantest.nooroweather.api.ApiResult
import com.ivantest.nooroweather.model.api.Error
import com.ivantest.nooroweather.model.api.WeatherError
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

/**
 * Helps parse a http response body to a corresponding
 * app WeatherError model.
 */
fun ResponseBody.parseError(): WeatherError {
    return try {
        val gson = Gson()
        val errorBody = this.string()
        errorBody.let {
            val errorResponse = gson.fromJson(it, WeatherError::class.java)
            errorResponse
        }
    } catch (e: Exception) {
        WeatherError()
    }
}

/**
 * Wrapper for api call, handles happy & edge cases with each api call.
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                ApiResult.Success(it)
            } ?: ApiResult.Error(WeatherError(Error(message = "Response body is null")))
        } else {
            val errorMessage = response.errorBody()?.parseError()
            ApiResult.Error(errorMessage)
        }
    } catch (e: IOException) {
        Log.e("APICall", "Call failed with IO exception", e)
        ApiResult.Error(WeatherError(Error(message = "Network connectivity error occured")))
    } catch (e: Exception) {
        Log.e("APICall", "Call failed with exception", e)
        ApiResult.Error(WeatherError(Error(message ="Unexpected error: ${e.message}")))
    }
}
