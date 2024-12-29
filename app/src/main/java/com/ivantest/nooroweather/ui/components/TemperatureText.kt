package com.ivantest.nooroweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivantest.nooroweather.R

@Composable
fun TemperatureText(temperature: String) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = temperature,
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 64.sp),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(4.dp),
            text = stringResource(R.string.degrees),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Light),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TemperatureTextPreview() {
    TemperatureText("31")
}
