package com.ivantest.nooroweather.di

import android.content.Context
import com.ivantest.nooroweather.api.WeatherApiService
import com.ivantest.nooroweather.data.WeatherRepository
import com.ivantest.nooroweather.data.WeatherRepositoryImpl
import com.ivantest.nooroweather.store.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApiService(): WeatherApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log request and response bodies
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(WeatherApiService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(apiService: WeatherApiService): WeatherRepository =
        WeatherRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}