package com.ivantest.nooroweather.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ivantest.nooroweather.R
import com.ivantest.nooroweather.model.Weather

@Composable
fun WeatherResultCard(
    weather: Weather,
    onClick: () -> Unit = {}
) {
    val (city, temperature, iconUrl) = weather

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .wrapContentHeight(unbounded = true)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,

            ) {
                Text(
                    text = city,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                )
                TemperatureText("$temperature")
            }
            AsyncImage(
                model = iconUrl,
                contentDescription = stringResource(R.string.weather_icon_content_description),
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview()
@Composable
fun WeatherResultCardPreview() {
    WeatherResultCard(
        weather = Weather(
            city = "Atlanta",
            iconUrl = "https://cdn.weatherapi.com/weather/64x64/day/176.png",
            temperature = 29
        )
    )
}