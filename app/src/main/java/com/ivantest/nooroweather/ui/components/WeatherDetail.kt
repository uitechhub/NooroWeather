package com.ivantest.nooroweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ivantest.nooroweather.R

@Composable
fun WeatherDetail(
    cityName: String,
    feelsLike: String,
    humidity: String,
    iconURL: String,
    temperature: String,
    uv: String
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AsyncImage(
            model = iconURL,
            contentDescription = stringResource(R.string.weather_condition_icon_content_description),
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground), // Optional placeholder
            error = painterResource(id = R.drawable.ic_launcher_foreground) // Optional error fallback
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = cityName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(R.drawable.location_fill),
                    contentDescription = stringResource(R.string.location_icon_content_description),
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TemperatureText(temperature)
        }
        Spacer(modifier = Modifier.size(32.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDetailItem(label = stringResource(R.string.humidity), value = humidity)
                WeatherDetailItem(label = stringResource(R.string.uv), value = uv)
                WeatherDetailItem(label = stringResource(R.string.feels_like), value = feelsLike)
            }
        }
    }
}

@Composable
fun WeatherDetailItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            ),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherDetailPreview() {
    WeatherDetail(
        cityName = "Hyderabad",
        uv = "1",
        humidity = "76%",
        iconURL = "https://cdn.weatherapi.com/weather/64x64/day/176.png",
        feelsLike = "25°",
        temperature = "31"
    )
}